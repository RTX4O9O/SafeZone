package me.rtx4090.safezone.commands;

import me.rtx4090.safezone.area.AreaLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AutoComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("add", "remove", "list").stream()
                    .filter(subcommand -> subcommand.startsWith(args[0]))
                    .collect(Collectors.toList());
        } else if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
            return new ArrayList<>(AreaLoader.areas.keySet()).stream()
                    .filter(name -> name.startsWith(args[1]))
                    .collect(Collectors.toList());
        } else if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 2) {
                return List.of("<name>");
            } else if (args.length >= 3 && args.length <= 8) {
                if (commandSender instanceof Player player) {
                    switch (args.length) {
                        case 3, 6:
                            return Arrays.asList(String.valueOf(player.getLocation().getBlockX()), player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ());
                        case 4, 7:
                            return Arrays.asList(String.valueOf(player.getLocation().getBlockY()), player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ());
                        case 5, 8:
                            return List.of(String.valueOf(player.getLocation().getBlockZ()));
                    }
                } else {
                    return List.of("<coordinate>");
                }
            }
        }
        return new ArrayList<>();
    }
}