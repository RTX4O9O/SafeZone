package me.rtx4090.safezone.listener;

import me.rtx4090.safezone.area.AreaLoader;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class EventListener implements Listener {
    @EventHandler
    public void onMonsterSpawning(CreatureSpawnEvent event) {
        if (event.getEntity() instanceof Monster) {
            Vector spawnLocation = event.getLocation().toVector();
            for (BoundingBox safeZone : AreaLoader.areas.values()) {
                if (safeZone.contains(spawnLocation)) event.setCancelled(true);
            }
        }
    }
}
