package com.core.imperium.particles.particleeffects;

import com.core.imperium.particles.ParticleEffect;
import com.core.imperium.player.PlayerPlus;
import org.bukkit.Location;
import org.bukkit.Particle;

public class WaterEffect extends ParticleEffect {
    public WaterEffect(PlayerPlus playerPlus) {
        super(playerPlus);
        this.start(1);
    }

    @Override
    public void effect(){
        if (this.getPlayerPlus().getPlayer().getVelocity().getX() == 0 && this.getPlayerPlus().getPlayer().getVelocity().getZ() == 0) {
            return;
        }

        Location loc = this.getPlayerPlus().getPlayer().getLocation();
        Location first = loc.clone().add(0, 0.4, 0);
        Location second = loc.clone().add(0, 0.35, 0);
        Location third = loc.clone().add(0, 0.3, 0);

        this.getPlayerPlus().getPlayer().getWorld().spawnParticle(Particle.WATER_SPLASH, first, 0);
        this.getPlayerPlus().getPlayer().getWorld().spawnParticle(Particle.WATER_SPLASH, second, 0);
        this.getPlayerPlus().getPlayer().getWorld().spawnParticle(Particle.WATER_SPLASH, third, 0);
    }
}
