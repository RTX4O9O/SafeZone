package me.rtx4090.safezone.commands;

import me.rtx4090.safezone.area.AreaLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.util.BoundingBox;

public class AreaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            sendHelp(commandSender);
            return true;
        }
        switch (strings[0]) {
            case "add":
                if (strings.length < 8) {
                    sendHelp(commandSender);
                } else if (AreaLoader.areas.containsKey(strings[1])) {
                    commandSender.sendMessage("§cSafe zone already exists");
                } else {
                    try {
                        String name = strings[1];
                        double x1 = Double.parseDouble(strings[2]);
                        double y1 = Double.parseDouble(strings[3]);
                        double z1 = Double.parseDouble(strings[4]);
                        double x2 = Double.parseDouble(strings[5]);
                        double y2 = Double.parseDouble(strings[6]);
                        double z2 = Double.parseDouble(strings[7]);

                        BoundingBox box = new BoundingBox(x1, y1, z1, x2, y2, z2);
                        AreaLoader.areas.put(name, box);
                        commandSender.sendMessage("§aSafe zone added");
                    } catch (NumberFormatException e) {
                        commandSender.sendMessage("§cInvalid coordinates");
                    }
                }
                return true;
            case "remove":
                if (strings.length < 2) sendHelp(commandSender);
                if (AreaLoader.areas.remove(strings[1]) == null) {
                    commandSender.sendMessage("§cSafe zone not found");
                } else {
                    commandSender.sendMessage("§aSafe zone removed");
                }
                return true;
            case "list":
                commandSender.sendMessage("§aSafe zones:" + AreaLoader.areas.keySet());
                return true;
            default:
                sendHelp(commandSender);
                return true;
        }
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("§r---------------------------");
        sender.sendMessage("§r§bSafeZone v1.0 §rby §bRTX4090");
        sender.sendMessage("§r§7Define monster free areas in your server.");
        sender.sendMessage("");
        sender.sendMessage("§r/safezone §3- §rshow help and introduction");
        sender.sendMessage("§r/safezone add <name> <x1> <y1> <z1> <x2> <y2> <z2> §3- §radd a safe zone");
        sender.sendMessage("§r/safezone remove <name> §3- §rremove a safe zone");
        sender.sendMessage("§r/safezone list §3- §rlists all safe zones");
        sender.sendMessage("§r---------------------------");

    }
}
