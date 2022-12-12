package com.core.imperium.powers.custompowers;

import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
        Ability osmosis = new Ability(ChatColor.AQUA + "Osmosis", AbilityType.PASSIVE, "regenerate health faster while underwater");
        Ability atlantean = new Ability(ChatColor.DARK_AQUA + "Atlantean", AbilityType.PASSIVE, "breathe underwater");

        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(osmosis);
        this.powerIcon.getAbilities().add(atlantean);

        this.powerIcon.reloadIcon();
    }
}
