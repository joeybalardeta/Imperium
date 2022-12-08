package com.core.imperium.nexus;

import com.core.imperium.utils.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

public class Nexus {
    // hashmap for easy access to plugin data files
    public static HashMap<File, FileConfiguration> pluginFiles = new HashMap<File, FileConfiguration>();

    // declare plugin data files
    public static File settings;
    public static FileConfiguration settingsConfig;
    public static File playerData;
    public static FileConfiguration playerDataConfig;



    public FileIO fileIO;

    public void init() {
        fileIO = new FileIO();
        fileIO.openFiles();
        fileIO.scheduleFileTasks();

        for (Player online : Bukkit.getOnlinePlayers()) {
            fileIO.loadPlayerPlus(online);
        }

        Utils.consoleLog(Level.INFO, "Nexus (Data Storage/Management) online.");
    }


}