/*
 * Copyright (c) 2022-2022.
 * Author: Ghirimoldi Luca
 */

package com.company;

public class Player {

    private final String name;
    private int cntGamesPlayed = 0, cntWin = 0;
    //contatori combinazioni di vittoria
    private int cntRow1 = 0, cntRow2 = 0, cntRow3 = 0, cntCol1 = 0, cntCol2 = 0, cntCol3 = 0, cntDiag1 = 0, cntDiag2 = 0;

    public Player(String name) {
        this.name = name;
    }

    public void gamesPlayed() {
        cntGamesPlayed++;
    }

    public void winGame() {
        cntWin++;
    }

    public Player cloneP() {
        Player plCopy = new Player("Copy");
        plCopy.setCntCol1(cntCol1);
        plCopy.setCntCol2(cntCol2);
        plCopy.setCntCol3(cntCol3);
        plCopy.setCntDiag1(cntDiag1);
        plCopy.setCntDiag2(cntDiag2);
        plCopy.setCntRow1(cntRow1);
        plCopy.setCntRow2(cntRow2);
        plCopy.setCntRow3(cntRow3);
        return plCopy;
    }

    //contatori posizioni di vittoria
    public boolean move(int p) {
        switch (p) {
            case 1: {
                cntCol1++;
                cntRow1++;
                cntDiag1++;
                break;
            }
            case 2: {
                cntCol2++;
                cntRow1++;
                break;
            }
            case 3: {
                cntCol3++;
                cntRow1++;
                cntDiag2++;
                break;
            }
            case 4: {
                cntCol1++;
                cntRow2++;
                break;
            }
            case 5: {
                cntCol2++;
                cntRow2++;
                cntDiag2++;
                cntDiag1++;
                break;
            }
            case 6: {
                cntCol3++;
                cntRow2++;
                break;
            }
            case 7: {
                cntCol1++;
                cntRow3++;
                cntDiag2++;
                break;
            }
            case 8: {
                cntCol2++;
                cntRow3++;
                break;
            }
            case 9: {
                cntCol3++;
                cntRow3++;
                cntDiag1++;
                break;
            }
        }
        return cntCol1 == 3 || cntCol2 == 3 || cntCol3 == 3 || cntRow1 == 3 || cntRow2 == 3 || cntRow3 == 3 || cntDiag1 == 3 || cntDiag2 == 3;
    }

    @Override
    public String toString() {
        return (this.name + " ha vinto !!!");
    }

    public void resetAll() {
        cntWin = 0;
        cntGamesPlayed = 0;
    }

    public void resetCntsPlayer() {
        cntCol1 = 0;
        cntCol2 = 0;
        cntCol3 = 0;
        cntRow1 = 0;
        cntRow2 = 0;
        cntRow3 = 0;
        cntDiag1 = 0;
        cntDiag2 = 0;
    }

    //getter & setter

    public int getCntGamesPlayed() {
        return cntGamesPlayed;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return cntWin;
    }

    public void setCntRow1(int cntRow1) {
        this.cntRow1 = cntRow1;
    }

    public void setCntRow2(int cntRow2) {
        this.cntRow2 = cntRow2;
    }

    public void setCntRow3(int cntRow3) {
        this.cntRow3 = cntRow3;
    }

    public void setCntCol1(int cntCol1) {
        this.cntCol1 = cntCol1;
    }

    public void setCntCol2(int cntCol2) {
        this.cntCol2 = cntCol2;
    }

    public void setCntCol3(int cntCol3) {
        this.cntCol3 = cntCol3;
    }

    public void setCntDiag1(int cntDiag1) {
        this.cntDiag1 = cntDiag1;
    }

    public void setCntDiag2(int cntDiag2) {
        this.cntDiag2 = cntDiag2;
    }
}