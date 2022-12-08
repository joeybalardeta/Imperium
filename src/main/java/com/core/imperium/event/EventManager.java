package com.core.imperium.event;


import com.core.imperium.Imperium;

public class EventManager {
    private Imperium instance;

    public void init(){
        instance = Imperium.getInstance();

        instance.getServer().getPluginManager().registerEvents(new PlayerEvent(), instance);
        instance.getServer().getPluginManager().registerEvents(new GUIEvent(), instance);
    }
}
