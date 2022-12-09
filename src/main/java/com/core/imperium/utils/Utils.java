package com.core.imperium.utils;

import com.core.imperium.Imperium;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class Utils {
    public static void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Imperium" + ChatColor.WHITE + "] " + message);
    }

    public static void sendAdvancedMessage(Player player, TextComponent message){
        TextComponent prefix = new TextComponent(ChatColor.WHITE + "[" + ChatColor.GOLD + "Imperium" + ChatColor.WHITE + "] ");
        prefix.addExtra(message);
        player.spigot().sendMessage(prefix);
    }

    public static void sendError(Player player, String message){
        player.sendMessage(ChatColor.WHITE + "[" + ChatColor.GOLD + "Imperium" + ChatColor.WHITE + "] " + ChatColor.RED + message);
    }

    public static void consoleLog(Level level, String message){
        Imperium.getInstance().getLogger().log(level, Imperium.PLUGINNAME + ": " + message);
    }
}
