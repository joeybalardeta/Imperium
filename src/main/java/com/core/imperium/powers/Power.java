package com.core.imperium.powers;

import com.core.imperium.powers.custompowers.*;

import java.util.ArrayList;
import java.util.List;

public class Power {
    protected static List<Power> powerList = new ArrayList<>();
    protected float maxHealth;
    protected PowerIcon powerIcon;
    protected String powerString;

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
}
