/*
 * Copyright (c) 2022-2022.
 * Author: Ghirimoldi Luca
 */

package com.company;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class AI extends Player {
    private static final Random rnd = new Random();
    private List<JButton> listBtns, buttonsAvaible;

    public AI(String name) {
        super(name);
    }

    public JButton nextMove(Player p1, Object p2) {
        int pos = Integer.parseInt(buttonsAvaible.get(rnd.nextInt(buttonsAvaible.size())).getName()); //random button
        pos = findmoves(pos, p1); //mossa difensiva
        pos = findmoves(pos, (Player) p2); //mossa vincente
        for (JButton i : listBtns) if (i.isEnabled() && Integer.parseInt(i.getName()) == pos) return i;
        return null;
    }

    private int findmoves(int pos, Player p) {
        for (JButton i : buttonsAvaible)
            if (i.isEnabled() && p.cloneP().move(Integer.parseInt(i.getName()))) return Integer.parseInt(i.getName());
        return pos;
    }

    //setter

    public void setListBtns(List<JButton> list) {
        this.listBtns = list;
    }

    public void setListAvaible(List<JButton> list) {
        this.buttonsAvaible = list;
    }
}