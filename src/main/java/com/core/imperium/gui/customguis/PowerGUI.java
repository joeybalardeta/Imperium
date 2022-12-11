package com.core.imperium.gui.customguis;

import com.core.imperium.gui.GUI;
import com.core.imperium.powers.PowerIcon;
import com.core.imperium.powers.custompowers.*;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class PowerGUI  extends GUI {
    private List<PowerIcon> powerIcons;
    public PowerGUI() {
        super(6, ChatColor.DARK_GRAY + "Powers");

        this.powerIcons = new ArrayList<PowerIcon>();

        powerIcons.add(new Avian().getPowerIcon());
        powerIcons.add(new Pyro().getPowerIcon());
        powerIcons.add(new Hydro().getPowerIcon());
        powerIcons.add(new Frost().getPowerIcon());
        powerIcons.add(new Terran().getPowerIcon());



        for (int i = 0; i < this.powerIcons.size(); i++){
            this.setItem(i + 22 - this.powerIcons.size() / 2, powerIcons.get(i).getIcon());
        }


    }
}
