package com.core.imperium.powers;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PowerIcon {
    private final int MAXTEXTWIDTH = 50;
    private ItemStack icon;
    private List<String> lore;
    private String name;
    private String description;
    private List<Ability> abilities;

    public PowerIcon() {
        this.icon = new ItemStack(Material.AIR);
        this.name = "";
        this.description = "";
        this.abilities = new ArrayList<>();
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ability> getAbilities() {
        return this.abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public void reloadIcon() {
        ItemMeta meta = icon.getItemMeta();

        meta.setDisplayName(this.name);


        this.lore = new ArrayList<>();


        {
            // this.lore.add("");
            int index = 0;
            boolean first = true;

            String[] parts = this.description.split(" ");

            while (true) {
                String line = "" + ChatColor.DARK_GRAY + ChatColor.ITALIC;
                while (true) {
                    line += parts[index] + " ";

                    String extra = "";
                    if (index < parts.length - 2) {
                        extra = parts[index + 1] + " " + parts[index + 2] + " ";
                    }

                    if (ChatColor.stripColor(line + extra).length() > MAXTEXTWIDTH) {
                        index++;
                        break;
                    }

                    index++;

                    if (index == parts.length) {
                        break;
                    }
                }

                this.lore.add(line);

                if (index == parts.length) {
                    break;
                }
            }
        }

        this.lore.add("");
        // this.lore.add(ChatColor.WHITE + "Abilities: ");

        for (Ability ability : this.abilities) {
            int index = 0;
            boolean first = true;

            String[] parts = ability.getDescription().split(" ");

            while (true) {
                String line = "" + ChatColor.DARK_GRAY + ChatColor.ITALIC;
                while (true) {
                    if (first) {
                        line += ability.toString() + ChatColor.WHITE + " - " + ChatColor.DARK_GRAY + ChatColor.ITALIC;
                        first = false;
                    }

                    line += parts[index] + " ";

                    String extra = "";
                    if (index < parts.length - 2) {
                        extra = parts[index + 1] + " " + parts[index + 2] + " ";
                    }

                    if (ChatColor.stripColor(line + extra).length() > MAXTEXTWIDTH) {
                        index++;
                        break;
                    }

                    index++;

                    if (index == parts.length) {
                        break;
                    }
                }

                this.lore.add(line);

                if (index == parts.length) {
                    break;
                }
            }
        }

        meta.setLore(this.lore);
        icon.setItemMeta(meta);
    }
}
