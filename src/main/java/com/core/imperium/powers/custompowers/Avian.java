package com.core.imperium.powers.custompowers;

import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Avian extends Power {
    public Avian() {
        super();

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
}
