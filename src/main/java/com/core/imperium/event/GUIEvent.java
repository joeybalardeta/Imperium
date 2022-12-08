package com.core.imperium.event;

import com.core.imperium.player.PlayerPlus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GUIEvent implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (PlayerPlus.getPlayerPlus((Player) event.getPlayer()) != null){
            PlayerPlus.getPlayerPlus((Player) event.getPlayer()).setGUI(null);
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (PlayerPlus.getPlayerPlus((Player) event.getWhoClicked()).getGUI() != null){
            event.setCancelled(true);
        }
    }
}