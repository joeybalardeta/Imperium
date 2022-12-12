package com.core.imperium.event;

import com.core.imperium.gui.GUI;
import com.core.imperium.gui.customguis.PowerGUI;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.Power;
import com.core.imperium.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;

public class GUIEvent implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (PlayerPlus.getPlayerPlus((Player) event.getPlayer()) != null){
            PlayerPlus.getPlayerPlus((Player) event.getPlayer()).setGUI(null);
        }

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus((Player) event.getWhoClicked());
        GUI playerGUI = playerPlus.getGUI();

        if (playerGUI == null){
            return;
        }

        event.setCancelled(true);

        if (playerGUI instanceof PowerGUI) {
            int slot = event.getSlot();

            List<Power> powers = Power.getPowerList();

            int index = slot - 22 + (Power.getPowerList().size() / 2);

            if (index >= Power.getPowerList().size()) {
                return;
            }

            if (playerPlus.getPower() == null) {
                Power power = Power.getPowerList().get(index);

                playerPlus.setPower(power);
                String powerString = power.getPowerString();
                Utils.sendMessage(playerPlus.getPlayer(), "You chose the " + powerString.substring(0, 1).toUpperCase() + powerString.substring(1) + " power!");
            }
            else {
                Power power = Power.getPowerList().get(index);
                playerPlus.setPower(power);
                String powerString = power.getPowerString();
                Utils.sendMessage(playerPlus.getPlayer(), "You chose the " + powerString.substring(0, 1).toUpperCase() + powerString.substring(1) + " power!");

                // Utils.sendError(playerPlus.getPlayer(), "You already have a power!");
            }

            playerPlus.reloadPowerAugmentations();

            playerPlus.closeGUI();

        }
    }
}