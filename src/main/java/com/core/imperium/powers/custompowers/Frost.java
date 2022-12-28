package com.core.imperium.powers.custompowers;

import com.core.imperium.Imperium;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import com.core.imperium.utils.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Frost extends Power {
    public Frost() {
        super();

        this.powerString = "frost";

        this.particles = "snow";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.BLUE_ICE));

        this.powerIcon.setName(ChatColor.AQUA + "Frost");
        this.powerIcon.setDescription("Frosts are often depicted as lone wanderers, not much is known about them, just don't let them freeze you.");

        // abilities
        Ability arcticWind = new Ability(ChatColor.BLUE + "Arctic Wind", AbilityType.PASSIVE, "regenerate health faster at high altitudes and on snow/ice");
        Ability blackIce = new Ability(ChatColor.DARK_PURPLE + "Black Ice", AbilityType.ACTIVE, "chance to freeze enemies on hit");


        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(arcticWind);
        this.powerIcon.getAbilities().add(blackIce);

        this.powerIcon.reloadIcon();

        this.registerPowerTasks();
    }


    protected void registerPowerTasks() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperium.getInstance(), new Runnable() {
            @Override
            public void run(){
                for (Player online : Bukkit.getOnlinePlayers()) {
                    PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(online);
                    Power power = playerPlus.getPower();

                    if (!playerPlus.hasPower()) {
                        return;
                    }

                    if (online.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                        return;
                    }

                    if (power instanceof Frost) {
                        if (online.getLocation().getBlockY() >= 140) {
                            if (!playerPlus.hasPotionEffect(PotionEffectType.REGENERATION, 0, 20)) {
                                online.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, false, false, false));
                            }
                        }

                        if (playerPlus.isOnBlock(Material.ICE) || playerPlus.isOnBlock(Material.BLUE_ICE)
                                || playerPlus.isOnBlock(Material.FROSTED_ICE) || playerPlus.isOnBlock(Material.PACKED_ICE)
                                || playerPlus.isOnBlock(Material.SNOW_BLOCK) || playerPlus.isOnBlock(Material.POWDER_SNOW)
                                || playerPlus.isInBlock(Material.POWDER_SNOW) || playerPlus.isInBlock(Material.SNOW)
                                || playerPlus.isInBlock(Material.POWDER_SNOW_CAULDRON)) {
                            if (!playerPlus.hasPotionEffect(PotionEffectType.REGENERATION, 0, 20)) {
                                online.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, false, false, false));
                                online.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0, false, false, false));
                            }
                        }
                    }
                }
            }
        }, 0, 2L);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus((Player) event.getDamager());

        if (playerPlus.getPower() instanceof Frost) {
            if (Utils.RNG(0.3)) {
                event.getEntity().setFreezeTicks(event.getEntity().getFreezeTicks() + 20);
            }
        }
    }

    @EventHandler
    public void onDamageTaken(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus((Player) event.getEntity());

        if (!playerPlus.hasPower()) {
            return;
        }

        if (playerPlus.getPower() instanceof Frost) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FREEZE) {
                event.setCancelled(true);
            }
        }
    }
}
