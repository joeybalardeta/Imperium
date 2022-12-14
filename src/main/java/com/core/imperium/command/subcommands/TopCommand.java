package com.core.imperium.command.subcommands;

import com.core.imperium.Imperium;
import com.core.imperium.command.SubCommand;
import com.core.imperium.player.PlayerPlus;
import com.core.imperium.powers.custompowers.Terran;
import com.core.imperium.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TopCommand extends SubCommand {
    public TopCommand(){
        this.subCommandName = "top";
        this.subCommandInfo = "teleport to the top block at your location";
        this.subCommandAliases = new String[0];
    }

    @Override
    public void onCommand(Player p, String[] args) {
        PlayerPlus playerPlus = PlayerPlus.getPlayerPlus(p);
        if (!playerPlus.hasPower()) {
            return;
        }

        if (!(playerPlus.getPower() instanceof Terran)) {
            Utils.sendError(p, "Only Terrans can use this power!");
            return;
        }

        // teleport player to top y-coordinate in their location(don't use in the nether please)
        Location l;

        if (p.getWorld().getEnvironment().equals(World.Environment.NETHER)){
            Utils.sendError(p, "You cannot use '/imm top' in the Nether!");
            return;
        }

        // safeguards so people don't get tp'ed into lava
        Location lCheck = new Location(p.getWorld(), p.getLocation().getX(), p.getWorld().getHighestBlockYAt(p.getLocation()) - 1, p.getLocation().getZ());
        int xPlus = 0;

        while (lCheck.getBlock().getType() == Material.LAVA) {
            xPlus += 10;
            lCheck = new Location(p.getWorld(), p.getLocation().getX() + xPlus, p.getWorld().getHighestBlockYAt(p.getLocation()) - 1, p.getLocation().getZ());
        }

        l = new Location(p.getWorld(), p.getLocation().getX() + xPlus, p.getWorld().getHighestBlockYAt(p.getLocation()) + 1, p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());

        p.teleport(l);
    }
}