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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Terran extends Power {
    public Terran() {
        super(24f);

        this.powerString = "terran";

        this.particles = "terran";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.GRASS_BLOCK));

        this.powerIcon.setName(ChatColor.DARK_GREEN + "Terran");
        this.powerIcon.setDescription("From the core of the Earth, Terrans boast sturdy bodies and ground based abilities.");

        // abilities
        Ability mountain = new Ability(ChatColor.GRAY + "Mountain", AbilityType.PASSIVE, "have two more hearts of health");
        Ability geothermics = new Ability(ChatColor.GREEN + "Geothermics", AbilityType.PASSIVE, "regenerate health faster while deep underground");
        Ability terraportation = new Ability(ChatColor.DARK_RED + "Terraportation", AbilityType.ACTIVE, "teleport upwards through the ground at will");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(mountain);
        this.powerIcon.getAbilities().add(geothermics);
        this.powerIcon.getAbilities().add(terraportation);

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

                    if (!playerPlus.hasPower()) {
                        return;
                    }

                    if (online.getWorld().getEnvironment().equals(World.Environment.NETHER)) {
                        return;
                    }

                    if (power instanceof Terran) {

                        if (online.getLocation().getBlockY() <= 40) {
                            if (!playerPlus.hasPotionEffect(PotionEffectType.REGENERATION, 0, 20)) {
                                online.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, false, false, false));
                            }
                        }
                    }
                }
            }
        }, 0, 10L);
    }
}
