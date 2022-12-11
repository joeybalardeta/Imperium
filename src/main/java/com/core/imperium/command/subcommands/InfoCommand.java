package com.core.imperium.command.subcommands;

import com.core.imperium.Imperium;
import com.core.imperium.command.SubCommand;
import com.core.imperium.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InfoCommand extends SubCommand {
    public InfoCommand(){
        this.subCommandName = "info";
        this.subCommandInfo = "Print out info about the Imperium plugin";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        Utils.sendMessage(p, ChatColor.AQUA + "Plugin Name: " + ChatColor.WHITE + Imperium.PLUGINNAME);
        Utils.sendMessage(p, ChatColor.AQUA + "Authors: " + ChatColor.WHITE + "Joey Balardeta");
        Utils.sendMessage(p, ChatColor.AQUA + "Version: " + ChatColor.WHITE + Imperium.VERSION);

        TextComponent message = new TextComponent(ChatColor.GOLD + "GitHub");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/joeybalardeta/Imperium"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GOLD + "" + ChatColor.ITALIC + "Visit the GitHub repository!")));
        Utils.sendAdvancedMessage(p, message);
    }
}