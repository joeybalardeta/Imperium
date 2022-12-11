package com.core.imperium.powers;

import net.md_5.bungee.api.ChatColor;

public class Ability {
    private String name;
    private AbilityType abilityType;
    private String description;

    public Ability(String name, AbilityType abilityType, String description) {
        this.name = name;
        this.abilityType = abilityType;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbilityType getAbilityType() {
        return this.abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        String ability = "";
        if (abilityType == AbilityType.PASSIVE) {
            ability = ChatColor.BLUE + "Passive";
        }
        else if (abilityType == AbilityType.ACTIVE) {
            ability = ChatColor.RED + "Active";
        }
        else if (abilityType == AbilityType.HYBRID) {
            ability = ChatColor.GREEN + "Hybrid";
        }

        return ChatColor.WHITE + "[" + ability + ChatColor.WHITE + "] " + this.name;
    }
}
