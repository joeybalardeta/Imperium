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
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class Pyro extends Power {
    public Pyro() {
        super();

        this.powerString = "pyro";

        this.particles = "flame";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.BLAZE_POWDER));

        this.powerIcon.setName(ChatColor.DARK_RED + "Pyro");
        this.powerIcon.setDescription("Born in the nether, Pyros have harnessed the power of fire to fuel themselves in battle.");

        // abilities
        Ability obsidianSkin = new Ability(ChatColor.LIGHT_PURPLE + "Obsidian Skin", AbilityType.PASSIVE, "immune to fire damage");
        Ability thermalVeil = new Ability(ChatColor.AQUA + "Thermal Veil", AbilityType.PASSIVE, "enemies in the Nether ignore you");
        Ability firestorm = new Ability(ChatColor.RED + "Firestorm", AbilityType.ACTIVE, "deal more damage while on fire");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(obsidianSkin);
        this.powerIcon.getAbilities().add(thermalVeil);
        this.powerIcon.getAbilities().add(firestorm);

        this.powerIcon.reloadIcon();

        this.registerPowerTasks();
    }

    protected void registerPowerTasks() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperium.getInstance(), new Runnable() {
            @Override
            public void run(){

            }
        }, 0, 5L);
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus((Player) event.getDamager());

        if (playerPlus.getPower() instanceof Pyro) {
            if (Utils.RNG(0.3)) {
                event.getEntity().setFireTicks(20);
            }
            if (event.getDamager().getFireTicks() != 0) {
                event.setDamage(event.getDamage() * 1.2);
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

        if (playerPlus.getPower() instanceof Pyro) {
            if (event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE_TICK || event.getCause() == DamageCause.HOT_FLOOR || event.getCause() == DamageCause.LAVA || event.getCause() == DamageCause.MELTING) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent event) {
        if (!(event.getTarget() != null && event.getTarget() instanceof Player)) {
            return;
        }

        Player target = (Player) event.getTarget();
        PlayerPlus targetPlus = PlayerPlus.getPlayerPlus(target);

        if (!(targetPlus.hasPower() && targetPlus.getPower() instanceof Pyro)) {
            return;
        }

        if (target.getWorld().getEnvironment() != World.Environment.NETHER) {
            return;
        }

        Entity entity = event.getEntity();

        if (entity instanceof Wolf || entity instanceof Cat || entity instanceof Parrot || entity instanceof Horse
                || entity instanceof Ocelot || entity instanceof Donkey || entity instanceof Axolotl) {
            return;
        }

        event.setCancelled(true);
    }
}
