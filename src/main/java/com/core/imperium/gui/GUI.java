package com.core.imperium.gui;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {
    private final Inventory inventory;
    private final int slots;
    private String name;


    public GUI(int rows, String name) {
        this.inventory = Bukkit.createInventory(null, rows * 9, ChatColor.GOLD + "Imperium" + ChatColor.DARK_GRAY + " | " + name);
        this.slots = rows * 9;
        this.name = ChatColor.stripColor(name);
    }

    public Inventory getInventory() {
        return inventory;
    }

    // NOTE: this method does not change the inventory name for the user, it just changes the GUI's name in the class object
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

   public void setItem(int slot, ItemStack item) {
       this.inventory.setItem(slot, item);
   }

    // set the background of the gui
    public void setBackground(Material material) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);


        for (int i = 0; i < 54; i++) {
            this.inventory.setItem(i, item);
        }
    }

    // close menu button
    public void setCloseMenuButton(int slot){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.RED + "Close Menu");

        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(slot, item);
    }

    // go back one GUI button
    public void setBackMenuButton(int slot){
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.GOLD + "Go Back");

        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(slot, item);
    }

    // previous page button
    public void setPreviousPageButton(int slot){
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.AQUA + "Previous Page");

        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(slot, item);
    }

    // next page button
    public void setNextPageButton(int slot){
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.AQUA + "Next Page");

        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(slot, item);
    }
}
