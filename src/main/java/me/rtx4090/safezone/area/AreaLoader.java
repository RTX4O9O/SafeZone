package me.rtx4090.safezone.area;

import me.rtx4090.safezone.utils.ConfigUtil;
import org.bukkit.util.BoundingBox;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class AreaLoader {
    public static Map<String, BoundingBox> areas = new HashMap<>();

    public static void load() {
        Path configPath = ConfigUtil.ofPath("area.yml");
        if (!Files.exists(configPath)) {
            ConfigUtil.copyDefault("area.yml");
        }
        ConfigUtil.readStatic(configPath, AreaLoader.class);
    }

    public static void save() {
        Path configPath = ConfigUtil.ofPath("area.yml");
        ConfigUtil.writeStatic(configPath, AreaLoader.class);
    }
}
