package com.core.imperium.nexus;

import com.core.imperium.Imperium;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.Power;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;

public class FileIO {
    public Imperium instance = Imperium.getInstance();
    public void openFiles() {
        // create/open plugin settings file
        Nexus.settings = new File(instance.getDataFolder(), "settings.yml");
        if (!Nexus.settings.exists()) {
            Nexus.settings.getParentFile().mkdirs();
            try {
                Nexus.settings.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.settingsConfig = YamlConfiguration.loadConfiguration(Nexus.settings);

        // create/open player data file
        Nexus.playerData = new File(instance.getDataFolder(), "playerData.yml");
        if (!Nexus.playerData.exists()) {
            Nexus.playerData.getParentFile().mkdirs();
            try {
                Nexus.playerData.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Nexus.playerDataConfig = YamlConfiguration.loadConfiguration(Nexus.playerData);

        Nexus.pluginFiles.put(Nexus.settings, Nexus.settingsConfig);
        Nexus.pluginFiles.put(Nexus.playerData, Nexus.playerDataConfig);
    }

    // save all plugin data files
    public void save(){
        for (Map.Entry<File, FileConfiguration> entry : Nexus.pluginFiles.entrySet()){
            try {
                entry.getValue().save(entry.getKey());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // Imperium.getInstance().getLogger().log(Level.INFO, "Nexus | Writing data to storage.");
    }



    public void writePlayerPlus(PlayerPlus playerPlus){
        Nexus.playerDataConfig.set("users." + playerPlus.getPlayer().getUniqueId() + ".name", playerPlus.getPlayer().getDisplayName());
        if (playerPlus.hasPower()) {
            Nexus.playerDataConfig.set("users." + playerPlus.getPlayer().getUniqueId() + ".power", playerPlus.getPower().getPowerString());
        }

    }

    public void loadPlayerPlus(Player p){
        PlayerPlus playerPlus = new PlayerPlus(p);
        Power playerPower = null;
        for (Power power : Power.getPowerList()) {
            if (power.getPowerString().equals(Nexus.playerDataConfig.get("users." + playerPlus.getPlayer().getUniqueId() + ".power"))) {
                playerPower = power;
            }
        }
        playerPlus.setPower(playerPower);
        playerPlus.reloadPowerAugmentations();
    }

    public void scheduleFileTasks(){
        BukkitScheduler scheduler = Imperium.getInstance().getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(Imperium.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    Imperium.nexus.fileIO.writePlayerPlus(PlayerPlus.getPlayerPlus(online));
                }

                Imperium.nexus.fileIO.save(); // save all plugin data files
            }
        }, 0L, 3600L);

    }
}