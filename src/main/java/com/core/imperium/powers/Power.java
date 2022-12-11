package com.core.imperium.powers;

public class Power {
    protected float maxHealth;
    protected PowerIcon powerIcon;

    public Power() {
        this.maxHealth = 20f;
    }

    public Power(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getMaxHealth() {
        return this.maxHealth;
    }

    public PowerIcon getPowerIcon() {
        return this.powerIcon;
    }
}
