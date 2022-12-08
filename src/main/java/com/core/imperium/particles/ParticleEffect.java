package com.core.imperium.particles;

import com.core.imperium.Imperium;
import com.core.imperium.player.PlayerPlus;
import org.bukkit.Bukkit;
import org.bukkit.Location;


public class ParticleEffect {
    private int taskID;

    private PlayerPlus playerPlus;

    private Location location;

    public ParticleEffect(PlayerPlus playerPlus) {
        this.playerPlus = playerPlus;
    }

    public ParticleEffect(Location location) {
        this.location = location;
    }

    public PlayerPlus getPlayerPlus(){
        return playerPlus;
    }

    public void end() {

        Bukkit.getScheduler().cancelTask(taskID);
    }

    public void start(int delay) {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperium.getInstance(), new Runnable() {
            @Override
            public void run(){
                effect();
            }
        }, 0, delay);
    }

    public void effect() {

    }
}
