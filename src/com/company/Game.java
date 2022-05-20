/*
 * Copyright (c) 2022-2022.
 * Author: Ghirimoldi Luca
 */

package com.company;

import com.company.MessageDialog.MessageDialogPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {

    public final AI p2;
    private final Player p1;
    private final boolean singlePlayer;
    private JPanel panel;
    private JLabel nameP1, pointP1, nameP2, pointP2, gameCount, nextTurn;
    private JTextField alertBox;
    private boolean player = true, end = false;
    private int countMoves = 0;

    private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, resetButton, interrompiPartitaButton, infoButton, aiutoButton;
    public final List<JButton> listButton = List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9);
    private List<JButton> listButtonAvaible = new ArrayList<>(listButton);

    public Game(String nameP1, String nameP2, Boolean b) {
        //window setting
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setResizable(true);
        this.pack();

        //centra la finestra
        Toolkit tk = getToolkit();
        Dimension size = tk.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        //Icon
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
            this.setIconImage(icon);
        } catch (Exception ignored) {
        }

        //disabled button text color
        UIManager.put("Button.disabledText", new ColorUIResource(Color.black));

        this.singlePlayer = b;
        p1 = new Player(nameP1);
        this.p2 = new AI(nameP2);
        if (this.singlePlayer) {
            this.p2.setListBtns(listButton);
            this.p2.setListAvaible(listButton);
        }

        //set data & styles
        loadData();
        displayNextTurn(p1.getName());
        setBorderGui();

        //set buttons name and text
        for (int i = 0; i < listButton.size(); i++) {
            listButton.get(i).setName((String.valueOf(i + 1)));
            listButton.get(i).setText(" ");
        }

        //action listener
        b1.addActionListener(e -> buttonPressed(b1));
        b2.addActionListener(e -> buttonPressed(b2));
        b3.addActionListener(e -> buttonPressed(b3));
        b4.addActionListener(e -> buttonPressed(b4));
        b5.addActionListener(e -> buttonPressed(b5));
        b6.addActionListener(e -> buttonPressed(b6));
        b7.addActionListener(e -> buttonPressed(b7));
        b8.addActionListener(e -> buttonPressed(b8));
        b9.addActionListener(e -> buttonPressed(b9));
        resetButton.addActionListener(e -> resetPoints());
        interrompiPartitaButton.addActionListener(e -> interrompiPartita());
        infoButton.addActionListener(e -> new MessageDialogPanel());
        aiutoButton.addActionListener(e -> openWebPage());
    }

    public void buttonPressed(JButton b) {
        char inputPlayer = player ? 'X' : 'O';
        player = !player;
        countMoves++;
        b.setEnabled(false);
        b.setText(String.valueOf(inputPlayer));
        setAlertBox("");
        listButtonAvaible.remove(b);
        p2.setListAvaible(listButtonAvaible);
        if (!player) {
            displayNextTurn(p2.getName());
            if (p1.move(Integer.parseInt(b.getName()))) { //controllo vincita player 1
                end(this.p1);  //vincita p1
                end = true;
            }
        } else {
            displayNextTurn(p1.getName());
            if (p2.move(Integer.parseInt(b.getName()))) { //controllo vincita player 2
                end(this.p2);  //vincita p2
                end = true;
            }
        }
        if (end) {
            interrompiPartitaButton.setText("Nuova Partita");
            interrompiPartitaButton.setBackground(Color.green);
            interrompiPartitaButton.setForeground(Color.black);
            p1.gamesPlayed();
            p2.gamesPlayed();
            reloadPoints();
            for (JButton i : listButton) {
                i.setEnabled(false);
            }
            countMoves = 0;
        } else if (countMoves == 9) {
            interrompiPartitaButton.setText("Nuova Partita");
            interrompiPartitaButton.setBackground(Color.green);
            interrompiPartitaButton.setForeground(Color.black);
            p1.gamesPlayed();
            p2.gamesPlayed();
            end();
            reloadPoints();
        }

        if (!player && countMoves < 8 && !end && singlePlayer) {
            p2.setListAvaible(listButtonAvaible);
            JButton btnPressedByAi = p2.nextMove(p1, p2);
            buttonPressed(btnPressedByAi);
        }
    }

    private void openWebPage() {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.wikihow.it/Vincere-a-Tris"));
        } catch (java.io.IOException e) {
            System.out.println("Impossibile aprire il sito" + e.getMessage());
        }
    }

    private void interrompiPartita() {
        interrompiPartitaButton.setText("Interrompi Partita");
        interrompiPartitaButton.setBackground(Color.red);
        interrompiPartitaButton.setForeground(Color.white);
        listButtonAvaible = new ArrayList<>(listButton);
        p2.setListAvaible(listButtonAvaible);
        resetGame();
        reloadPoints();
        alertBox.setText("");
        player = true;
    }

    private void loadData() {
        nameP1.setText("X:  " + p1.getName());
        nameP2.setText("O:  " + p2.getName());
        pointP1.setText(String.valueOf(p1.getWins()));
        pointP2.setText(String.valueOf(p2.getWins()));
        gameCount.setText(String.valueOf(p1.getCntGamesPlayed()));
    }

    private void reloadPoints() {
        pointP1.setText(String.valueOf(p1.getWins()));
        pointP2.setText(String.valueOf(p2.getWins()));
        gameCount.setText(String.valueOf(p1.getCntGamesPlayed()));
    }

    public void setAlertBox(String message) {
        alertBox.setText(message);
    }


    private void panelReset() {
        for (JButton i : listButton) {
            i.setText(" ");
            i.setEnabled(true);
        }
    }

    private void resetGame() {
        //reset contatori vincita dei due giocatori
        p1.resetCntsPlayer();
        p2.resetCntsPlayer();
        //stato dei bottoni iniziale
        panelReset();
        countMoves = 0;
        end = false;
    }

    private void resetPoints() {
        p1.resetAll();
        p2.resetAll();
        reloadPoints();
        alertBox.setText("Punti resettati");
    }

    private void displayNextTurn(String name) {
        nextTurn.setText(name);
    }

    private void setBorderGui() {
        Border brdDarkGreen = BorderFactory.createLineBorder(new Color(10, 156, 40));
        for (JButton i : listButton) i.setBorder(brdDarkGreen);
        alertBox.setBorder(brdDarkGreen);
    }

    private void end(Player p) {
        p.winGame();
        Start.game.setAlertBox(p.toString());
    }

    private void end() {
        Start.game.setAlertBox("Pareggio!");
    }
}