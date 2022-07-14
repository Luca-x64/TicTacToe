/*
 * Copyright (c) 2022-2022.
 * Author: Ghirimoldi Luca
 */

package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Start extends JFrame {
    public static Game game;
    private final AI ai;
    private JPanel panel;
    private JLabel p1Head, p2Head, titleLabel;
    private JTextField textFieldNameP1, textFieldNameP2;
    private JButton onePlayerBtn, twoPlayerBtn, playButton;
    private boolean singleplayer; //true = 1 player, false = 2 player

    public Start() {
        //window setting
        super("Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setVisible(true);
        this.setResizable(true);
        this.setResizable(false);
        this.pack();

        //centra finestra
        Toolkit tk = getToolkit();
        Dimension size = tk.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        //icon
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
            this.setIconImage(icon);
        } catch (Exception ignored) {
        }

        ai = new AI("AI");

        //stile
        style();

        //Action Listener
        onePlayerBtn.addActionListener(e -> onePlayer());
        twoPlayerBtn.addActionListener(e -> twoPlayer());
        playButton.addActionListener(e -> play());
    }

    private void play() {
        if (singleplayer) { // 1 player
            if (textFieldNameP1.getText().isEmpty()) {
                System.out.println("Il nome non può essere vuoto!");
            } else {
                Start.super.setVisible(false);
                String nameP1 = textFieldNameP1.getText();
                game = new Game(nameP1, ai.getName(), singleplayer);
                game.setSize(650, 600);
                game.setVisible(true);
                game.setResizable(false);
            }
        } else { //2 player
            if (textFieldNameP1.getText().isEmpty() || textFieldNameP2.getText().isEmpty()) {
                System.out.println("I nomi non posso essere vuoti!");
            } else {
                Start.super.setVisible(false);
                String nameP1 = textFieldNameP1.getText();
                String nameP2 = textFieldNameP2.getText();
                game = new Game(nameP1, nameP2, singleplayer);
                game.setSize(650, 600);
                game.setVisible(true);
                game.setResizable(false);
            }
        }
    }

    private void style() {
        Border brd = BorderFactory.createLineBorder(new Color(10, 156, 40));
        UIManager.put("Button.select", new Color(91,255,97));
        titleLabel.setVisible(false);
        textFieldNameP1.setBorder(brd);
        textFieldNameP2.setBorder(brd);
        textFieldNameP1.setVisible(false);
        textFieldNameP2.setVisible(false);
        p1Head.setVisible(false);
        p2Head.setVisible(false);
        playButton.setEnabled(false);
    }

    private void onePlayer() {
        titleLabel.setVisible(true);
        playButton.setEnabled(true);
        singleplayer = true;
        //p1
        onePlayerBtn.setVisible(false);
        textFieldNameP1.setVisible(true);
        p1Head.setVisible(true);
        //p2
        twoPlayerBtn.setVisible(false);
        textFieldNameP2.setVisible(true);
        textFieldNameP2.setText(this.ai.getName());
        textFieldNameP2.setEnabled(false);
        p2Head.setVisible(true);

    }

    private void twoPlayer() {
        titleLabel.setVisible(true);
        playButton.setEnabled(true);
        singleplayer = false;
        //p1
        onePlayerBtn.setVisible(false);
        textFieldNameP1.setVisible(true);
        p1Head.setVisible(true);
        //p2
        twoPlayerBtn.setVisible(false);
        textFieldNameP2.setVisible(true);
        textFieldNameP2.setText("");
        textFieldNameP2.setEnabled(true);
        textFieldNameP2.setEditable(true);
        p2Head.setVisible(true);

    }
}