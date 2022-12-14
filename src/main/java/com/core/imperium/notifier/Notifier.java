package com.core.imperium.notifier;

import com.core.imperium.Imperium;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.custompowers.Hydro;
import com.core.imperium.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Notifier {
    public void scheduleNotifier() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Imperium.getInstance(), new Runnable() {
            @Override
            public void run(){
                Imperium.notifier.notifyPlayers();
            }
        }, 0, 6000L);
    }

    public void notifyPlayers() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(online);

            if (!playerPlus.hasPower()) {
                Utils.sendMessage(online, "Please pick a power by using '/imm menu'! Use '/imm help' for info on other Imperium commands!");
            }
        }
    }
}
