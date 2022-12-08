package com.core.imperium.event;

import com.core.imperium.Imperium;
import com.core.imperium.player.PlayerPlus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
        PlayerPlus.getPlayerPlus(event.getPlayer()).removePlayerPlus();
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player p = event.getPlayer();
    }
}
