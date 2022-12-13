package com.core.imperium.powers.custompowers;

import com.core.imperium.Imperium;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Avian extends Power {
    public Avian() {
        super();

        this.powerString = "avian";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.FEATHER));

        this.powerIcon.setName(ChatColor.GOLD + "Avian");
        this.powerIcon.setDescription("Calling the sky their home, Avians are known for their harmony with the wind.");

        // abilities
        Ability blessedWind = new Ability(ChatColor.GREEN + "Blessed Wind", AbilityType.PASSIVE, "regenerate health faster at high altitudes");
        Ability airCushion = new Ability(ChatColor.AQUA + "Air Cushion", AbilityType.PASSIVE, "take no damage from falls");
        Ability nightHawk = new Ability(ChatColor.GOLD + "Night Hawk", AbilityType.ACTIVE, "crouching while flying with an Elytra increases speed");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(blessedWind);
        this.powerIcon.getAbilities().add(airCushion);
        this.powerIcon.getAbilities().add(nightHawk);

        this.powerIcon.reloadIcon();
    }

    @Override
    protected void registerPowerTasks() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperium.getInstance(), new Runnable() {
            @Override
            public void run(){
                for (Player online : Bukkit.getOnlinePlayers()) {
                    PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(online);
                    Power power = playerPlus.getPower();

                    if (power == null) {
                        return;
                    }

                    if (online.getWorld().getEnvironment().equals(Environment.NETHER)) {
                        return;
                    }

                    if (power instanceof Avian) {
                        if (online.getLocation().getBlockY() >= 140) {
                            if (!playerPlus.hasPotionEffect(PotionEffectType.REGENERATION, 0, 40)) {
                                online.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, false, false, true));
                            }
                        }
                    }
                }
            }
        }, 0, 10L);
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

        if (playerPlus.getPower() instanceof Avian) {
            if (event.getCause() == DamageCause.FALL || event.getCause() == DamageCause.FLY_INTO_WALL || event.getCause() == DamageCause.CONTACT) {
                event.setCancelled(true);
            }
        }
    }
}
