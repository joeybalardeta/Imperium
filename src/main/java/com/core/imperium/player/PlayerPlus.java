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

    public double getPlayerDirectionFloat() {
        double rotation = this.player.getLocation().getYaw() - 180;
        if (rotation < 0) {
            rotation += 360.0;
        }
        return rotation;
    }

    public void reloadMaxHealth() {
        if (this.getPower() == null) {
            this.getPlayer().setMaxHealth(20f);
            return;
        }

        this.getPlayer().setMaxHealth(this.getPower().getMaxHealth());
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
