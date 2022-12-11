package com.core.imperium.powers.custompowers;

import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Terran extends Power {
    public Terran() {
        super(24f);

        this.powerString = "terran";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.GRASS_BLOCK));

        this.powerIcon.setName(ChatColor.DARK_GREEN + "Terran");
        this.powerIcon.setDescription("From the core of the Earth, Terrans boast sturdy bodies and ground based abilities.");

        // abilities
        Ability mountain = new Ability(ChatColor.GRAY + "Mountain", AbilityType.PASSIVE, "have two more hearts of health");
        Ability geothermics = new Ability(ChatColor.GREEN + "Geothermics", AbilityType.PASSIVE, "regenerate health faster while deep underground");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(mountain);
        this.powerIcon.getAbilities().add(geothermics);

        this.powerIcon.reloadIcon();
    }
}
