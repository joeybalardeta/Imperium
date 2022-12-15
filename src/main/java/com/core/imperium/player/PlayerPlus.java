package com.core.imperium.player;

import com.core.imperium.Imperium;
import com.core.imperium.gui.GUI;
import com.core.imperium.particles.ParticleEffect;
import com.core.imperium.particles.particleeffects.FlameEffect;
import com.core.imperium.particles.particleeffects.LightningEffect;
import com.core.imperium.particles.particleeffects.SnowEffect;
import com.core.imperium.particles.particleeffects.WaterEffect;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.custompowers.Pyro;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class PlayerPlus {
    // the player object linked to the PlayerPlus object
    private Player player;

    // gui in player class is so their copy of the gui that they are viewing doesn't get replaced by another one
    private GUI gui;

    private Power power;

    private ParticleEffect particleEffect;

    private static HashMap<Player, PlayerPlus> bindedPlayerPluses = new HashMap<Player, PlayerPlus>();

    public PlayerPlus(Player player){
        this.player = player;
        bindedPlayerPluses.put(player, this);

        // this.setParticleEffect(new FlameEffect(PlayerPlus.getPlayerPlus(player)));
    }

    public void removePlayerPlus(){
        bindedPlayerPluses.remove(this.getPlayer());

        if (this.particleEffect != null) {
            this.particleEffect.end();
        }

        Imperium.nexus.fileIO.writePlayerPlus(this);
    }

    public void setParticleEffect(ParticleEffect particleEffect) {
        if (this.particleEffect != null){
            this.particleEffect.end();
        }

        this.particleEffect = particleEffect;
    }

    public GUI getGUI() {
        return this.gui;
    }

    public void setGUI(GUI gui) {
        this.gui = gui;
        if (this.gui != null) {
            this.getPlayer().openInventory(this.gui.getInventory());
        }
    }

    public void closeGUI() {
        this.getPlayer().closeInventory();
    }

    public Power getPower() {
        return this.power;
    }

    public void setPower(Power power) {
        this.power = power;
    }


    public ParticleEffect getParticleEffect(){
        return particleEffect;
    }

    public void endParticleEffect(){
        if (particleEffect == null){
            return;
        }

        particleEffect.end();
        particleEffect = null;
    }

    public static PlayerPlus getPlayerPlus(Player player){
        return bindedPlayerPluses.get(player);
    }

    public Player getPlayer(){
        return this.player;
    }

    public float getPlayerDirectionFloat() {
        float rotation = this.player.getLocation().getYaw() - 180;
        if (rotation < 0) {
            rotation += 360.0;
        }
        return rotation;
    }

    /**
     * Returns a normalized vector for the player's look direction
     * @return Vector
     */
    public Vector getLookVector() {
        return this.getPlayer().getLocation().getDirection().normalize();
    }

    public void reloadMaxHealth() {
        if (this.getPower() == null) {
            this.getPlayer().setMaxHealth(20f);
            return;
        }

        this.getPlayer().setMaxHealth(this.getPower().getMaxHealth());
    }

    /**
     * Returns a boolean detailing if the player has a set potion effect with
     * an equivalent amplification level and greater duration.
     * @param potionEffectType the type of potion effect to check that the player has
     * @param amplifier the amplifier of the potion effect (-1 to disable checking)
     * @param duration the duration (in ticks) of the potion effect (-1 to disable checking)
     * @return boolean
     */
    public boolean hasPotionEffect(PotionEffectType potionEffectType, int amplifier, int duration) {
        for (PotionEffect potionEffect : this.getPlayer().getActivePotionEffects()) {
            if (potionEffect.getType().equals(potionEffectType) && (potionEffect.getAmplifier() == amplifier || amplifier == -1) && (potionEffect.getDuration() >= duration || duration == -1)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPower() {
        return this.getPower() != null;
    }

    public void startPowerParticles() {
        if (this.getPower() == null) {
            return;
        }

        switch (this.getPower().getParticles()) {
            case "flame":
                this.setParticleEffect(new FlameEffect(this));
                break;

            case "snow":
                this.setParticleEffect(new SnowEffect(this));
                break;

            case "water":
                this.setParticleEffect(new WaterEffect(this));
                break;

            case "lightning":
                this.setParticleEffect(new LightningEffect(this));
                break;

            default:
                break;
        }

    }

    public void stopParticles() {
        if (this.particleEffect == null) {
            return;
        }

        this.particleEffect.end();
    }

    public void reloadPowerAugmentations() {
        this.reloadMaxHealth();
        this.stopParticles();
        this.startPowerParticles();
    }
}
