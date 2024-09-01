package me.rtx4090.safezone;

import me.rtx4090.safezone.area.AreaLoader;
import me.rtx4090.safezone.commands.AreaCommand;
import me.rtx4090.safezone.commands.AutoComplete;
import me.rtx4090.safezone.listener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SafeZone extends JavaPlugin {
    public static SafeZone Instance = null;
    public static Logger logger = null;

    @Override
    public void onEnable() {
        Instance = this;
        logger = this.getLogger();
        AreaLoader.load();
        getCommand("safezone").setExecutor(new AreaCommand());
        getCommand("safezone").setTabCompleter(new AutoComplete());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        logger.info("SafeZone plugin is enabled");
    }

    @Override
    public void onDisable() {

    }
}
