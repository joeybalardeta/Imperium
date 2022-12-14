package com.core.imperium.gui.customguis;

import com.core.imperium.gui.GUI;
import com.core.imperium.powers.Power;
import com.core.imperium.powers.PowerIcon;
import com.core.imperium.powers.custompowers.*;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PowerGUI  extends GUI {
    public PowerGUI() {
        super(6, "");

        List<Power> powers = Power.getPowerList();
        for (int i = 0; i < powers.size(); i++){
            this.setItem(i + 22 - powers.size() / 2, powers.get(i).getPowerIcon().getIcon());
        }
    }
}
