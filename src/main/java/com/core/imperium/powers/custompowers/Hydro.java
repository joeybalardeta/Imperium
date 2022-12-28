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
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Level;

public class Hydro extends Power {
    public Hydro() {
        super();

        this.powerString = "hydro";

        this.particles = "water";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.COD));

        this.powerIcon.setName(ChatColor.BLUE + "Hydro");
        this.powerIcon.setDescription("Originating from the depths of the sea, Hydros have gained the ocean's favor and can command its power at will.");

        // abilities
        Ability osmosis = new Ability(ChatColor.AQUA + "Osmosis", AbilityType.PASSIVE, "regenerate health faster while in water or in the rain");
        Ability atlantean = new Ability(ChatColor.DARK_AQUA + "Atlantean", AbilityType.PASSIVE, "permanent Conduit Power and Dolphin's Grace while in water");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(osmosis);
        this.powerIcon.getAbilities().add(atlantean);

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
                        continue;
                    }

                    if (online.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                        continue;
                    }

                    if (power instanceof Hydro) {
                        if (online.isInWater()) {
                            if (!playerPlus.hasPotionEffect(PotionEffectType.REGENERATION, 0, 20)) {
                                online.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, false, false, false));
                                online.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 100, 0, false, false, false));
                                online.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 0, false, false, false));
                            }
                        }

                        if (!online.getWorld().isClearWeather()) {
                            if (online.getLocation().getBlockY() >= (online.getWorld().getHighestBlockYAt(online.getLocation()))) {
                                if (!playerPlus.hasPotionEffect(PotionEffectType.REGENERATION, 0, 20)) {
                                    online.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, false, false, false));
                                }
                            }
                        }

                    }
                }
            }
        }, 0, 2L);
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

        if (playerPlus.getPower() instanceof Hydro) {
            if (event.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
                event.setCancelled(true);
            }
        }
    }
}
