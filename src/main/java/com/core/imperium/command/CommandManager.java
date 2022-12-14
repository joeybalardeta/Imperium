package com.core.imperium.command;

import com.core.imperium.Imperium;
import com.core.imperium.command.subcommands.InfoCommand;
import com.core.imperium.command.subcommands.MenuCommand;
import com.core.imperium.command.subcommands.TopCommand;
import com.core.imperium.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;

public class CommandManager implements CommandExecutor {
    private ArrayList<SubCommand> subCommands = new ArrayList<SubCommand>();
    private final Imperium instance = Imperium.getInstance();

    private final String rootCommand = "imm";

    public String getRootCommand(){
        return rootCommand;
    }

    public void init(){
        instance.getCommand(this.getRootCommand()).setExecutor(this);

        this.subCommands.add(new InfoCommand());
        this.subCommands.add(new MenuCommand());
        this.subCommands.add(new TopCommand());

        Utils.consoleLog(Level.INFO, "CommandManager (Plugin command parsing/routing) online.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can run Commands+ commands!");
            return false;
        }

        Player p = (Player) sender;

        if (!command.getName().equalsIgnoreCase(rootCommand)) {
            return false;
        }

        if (args.length == 0) {
            return false;
        }

        for (SubCommand sc : subCommands) {
            if (sc.getName().equals(args[0])){
                sc.onCommand(p, args);
            }
        }

        return false;
    }

    public ArrayList<SubCommand> getSubCommands(){
        return this.subCommands;
    }
}
