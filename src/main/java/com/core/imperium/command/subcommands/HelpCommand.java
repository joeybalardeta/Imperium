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

public class HelpCommand extends SubCommand {
    public HelpCommand(){
        this.subCommandName = "help";
        this.subCommandInfo = "list all of the plugin commands and their descriptions";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        Utils.sendMessage(p, ChatColor.YELLOW + "List of commands: ");
        for (SubCommand subCommand : Imperium.commandManager.getSubCommands()) {
            Utils.sendMessage(p, ChatColor.AQUA + subCommand.subCommandName + ChatColor.WHITE + " - " + ChatColor.YELLOW + subCommand.subCommandInfo);
        }
    }
}