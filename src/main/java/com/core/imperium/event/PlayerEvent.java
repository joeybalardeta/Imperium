package com.core.imperium.event;

import com.core.imperium.Imperium;
import com.core.imperium.nexus.Nexus;
import com.core.imperium.player.PlayerPlus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitScheduler;

public class PlayerEvent implements Listener {
    private final Imperium instance = Imperium.getInstance();


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Imperium.nexus.fileIO.loadPlayerPlus(event.getPlayer());

        // delayed event, probably gonna use this to send messages to users on join.
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(instance, new Runnable() {
            public void run() {

            }
        }, 10L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Imperium.nexus.fileIO.writePlayerPlus(PlayerPlus.getPlayerPlus(event.getPlayer()));
        PlayerPlus.getPlayerPlus(event.getPlayer()).removePlayerPlus();
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player p = event.getPlayer();
    }
}
