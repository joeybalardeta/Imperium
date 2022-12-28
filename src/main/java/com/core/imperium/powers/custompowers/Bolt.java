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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class Bolt extends Power {
    public Bolt() {
        super();

        this.powerString = "bolt";

        this.particles = "lightning";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.LIGHTNING_ROD));

        this.powerIcon.setName(ChatColor.YELLOW + "Bolt");
        this.powerIcon.setDescription("Bolts are the light that erupts before thunder, with the ability to control electrons at will, you might not want to get too close.");

        // abilities
        Ability currentSpike = new Ability(ChatColor.DARK_RED + "Current Spike", AbilityType.PASSIVE, "regenerate health faster, jump higher, and deal more damage during thunderstorms");
        Ability superconductor = new Ability(ChatColor.GOLD + "Superconductor", AbilityType.PASSIVE, "nearby lightning strikes amplify the Current Spike passive");
        Ability capacitor = new Ability(ChatColor.DARK_PURPLE + "Capacitor", AbilityType.ACTIVE, "launch forward when guarding with a shield");


        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(currentSpike);
        this.powerIcon.getAbilities().add(superconductor);
        this.powerIcon.getAbilities().add(capacitor);

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


                    if (power instanceof Bolt) {
                        Vector vel = online.getVelocity();

                        double total = 0.0;

                        total += Math.abs(online.getVelocity().getX());
                        total += Math.abs(online.getVelocity().getY());
                        total += Math.abs(online.getVelocity().getZ());

                        if (online.isBlocking() && total <= 0.1) {
                            Vector toAdd = playerPlus.getLookVector().multiply(5);
                            online.setVelocity(online.getVelocity().add(toAdd));

                            online.getWorld().strikeLightningEffect(online.getLocation());
                        }
                    }
                }
            }
        }, 0, 2L);
    }
}
