package com.core.imperium.command;

import com.core.imperium.Imperium;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompletionManager implements TabCompleter {
    private final ArrayList<SubCommand> subCommands = Imperium.commandManager.getSubCommands();

    public void init() {
        Imperium.getInstance().getCommand(Imperium.commandManager.getRootCommand()).setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if (!label.equals("imm")) {
            return tabComplete;
        }

        if (args.length == 1) {
            for (SubCommand subCommand : subCommands) {
                tabComplete.add(subCommand.getName());
            }
            return tabComplete;
        }


        return tabComplete;
    }
}
