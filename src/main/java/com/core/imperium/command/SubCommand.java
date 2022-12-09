package com.core.imperium.command;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class SubCommand {
    public SubCommand() {

    }

    // subcommand member variables
    public String subCommandName;

    public String subCommandInfo;

    public String[] subCommandAliases;

    // subcommand functions
    public void onCommand(Player p, String[] args){

    }

    public String getName(){
        return subCommandName;
    }

    public String getInfo(){
        return subCommandInfo;
    }

    public String[] getAliases(){
        return subCommandAliases;
    }
}