package com.core.imperium.powers.custompowers;

import com.core.imperium.powers.Ability;
import com.core.imperium.powers.AbilityType;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Frost extends Power {
    public Frost() {
        super();

        this.powerString = "frost";

        this.powerIcon = new PowerIcon();

        this.powerIcon.setIcon(new ItemStack(Material.BLUE_ICE));

        this.powerIcon.setName(ChatColor.AQUA + "Frost");
        this.powerIcon.setDescription("Frosts are often depicted as lone wanderers, not much is known about them, just don't let them freeze you.");

        // abilities
        Ability arcticWind = new Ability(ChatColor.BLUE + "Arctic Wind", AbilityType.PASSIVE, "regenerate health faster at high altitudes");
        Ability blackIce = new Ability(ChatColor.DARK_PURPLE + "Black Ice", AbilityType.ACTIVE, "chance to freeze enemies on hit");


        // adding the abilities to the GUI (specifically the lore of the ItemStack that is used to signify the power)
        this.powerIcon.getAbilities().add(arcticWind);
        this.powerIcon.getAbilities().add(blackIce);

        this.powerIcon.reloadIcon();
    }
}
