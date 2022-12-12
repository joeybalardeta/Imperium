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

        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(event.getPlayer());
        playerPlus.reloadPowerAugmentations();

        // delayed event, probably gonna use this to send messages to users on join.
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(instance, new Runnable() {
            public void run() {

            }
        }, 10L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(event.getPlayer());

        Imperium.nexus.fileIO.writePlayerPlus(playerPlus);

        playerPlus.stopParticles();

        playerPlus.removePlayerPlus();
    }

    @EventHandler
    public void onPortalEvent(PlayerPortalEvent event) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(event.getPlayer());
        playerPlus.reloadPowerAugmentations();
    }

    @EventHandler
    public void onRespawnEvent(PlayerRespawnEvent event) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(event.getPlayer());
        playerPlus.reloadPowerAugmentations();
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(event.getPlayer());

        playerPlus.stopParticles();
    }

    @EventHandler
    public void onBedExit(PlayerBedLeaveEvent event) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(event.getPlayer());

        playerPlus.startPowerParticles();
    }
}
