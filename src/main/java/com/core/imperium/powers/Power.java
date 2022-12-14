package com.core.imperium.powers;

import com.core.imperium.Imperium;
import com.core.imperium.particles.ParticleEffect;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.custompowers.*;
import com.core.imperium.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;

public class Power implements Listener {
    protected static List<Power> powerList = new ArrayList<>();
    protected float maxHealth;
    protected PowerIcon powerIcon;
    protected String powerString;
    protected String particles = "";

    public Power() {
        this.maxHealth = 20f;

        powerList.add(this);
    }

    public Power(float maxHealth) {
        this.maxHealth = maxHealth;

        powerList.add(this);
    }

    public static void initPowers() {
        new Avian();
        new Hydro();
        new Pyro();
        new Frost();
        new Terran();
        // new Bolt();

        for (Power power : powerList) {
            Imperium.getInstance().getServer().getPluginManager().registerEvents(power, Imperium.getInstance());
        }
    }

    public static List<Power> getPowerList() {
        return powerList;
    }

    public float getMaxHealth() {
        return this.maxHealth;
    }

    public PowerIcon getPowerIcon() {
        return this.powerIcon;
    }

    public String getPowerString() {
        return this.powerString;
    }

    public String getParticles() {
        return this.particles;
    }

    public void setParticles(String particles) {
        this.particles = particles;
    }
}
