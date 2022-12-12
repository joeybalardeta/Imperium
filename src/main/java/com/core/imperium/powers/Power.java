package com.core.imperium.powers;

import com.core.imperium.Imperium;
import com.core.imperium.particles.ParticleEffect;
import com.core.imperium.powers.custompowers.*;
import org.bukkit.event.Listener;

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
        // new Bolt(); it's not ready yet, more time needed to develop

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
