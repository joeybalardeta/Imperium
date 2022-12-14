package com.core.imperium.command.subcommands;

import com.core.imperium.command.SubCommand;
import com.core.imperium.gui.customguis.PowerGUI;
import com.core.imperium.player.PlayerPlus;
import org.bukkit.entity.Player;

import javax.swing.*;

public class MenuCommand extends SubCommand {
    public MenuCommand(){
        this.subCommandName = "menu";
        this.subCommandInfo = "open a GUI for the command sender";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        PlayerPlus.getPlayerPlus(p).setGUI(new PowerGUI());
    }
}