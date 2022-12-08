package com.core.imperium;

import com.core.imperium.event.EventManager;
import com.core.imperium.nexus.Nexus;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Imperium extends JavaPlugin {
    public static String VERSION = "0.1";
    public static String PLUGINNAME = "Imperium";
    private static Imperium instance;

    public static EventManager eventManager;
    public static Nexus nexus;

    public static Imperium getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        eventManager = new EventManager();
        eventManager.init();

        nexus = new Nexus();
        nexus.init();

        getLogger().log(Level.INFO, PLUGINNAME + " v" + VERSION + " is online!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        instance = null;
    }
}
