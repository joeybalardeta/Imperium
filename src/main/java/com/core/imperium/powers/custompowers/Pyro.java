package com.core.imperium.powers.custompowers;

import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
        Ability firestorm = new Ability(ChatColor.RED + "Firestorm", AbilityType.ACTIVE, "deal more damage while on fire");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(obsidianSkin);
        this.powerIcon.getAbilities().add(firestorm);

        this.powerIcon.reloadIcon();
    }
}
