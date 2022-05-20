/*
 * Copyright (c) 2022-2022.
 * Author: Ghirimoldi Luca
 */

package com.company.MessageDialog;

import javax.swing.*;
import java.awt.*;

public class MessageDialogPanel {
    public MessageDialogPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 255, 184));
        panel.setSize(new Dimension(200, 400));
        panel.setLayout(null);

        JLabel label = new JLabel("[Author] Ghirimoldi Luca");
        label.setBounds(0, 0, 200, 20);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label1 = new JLabel("[Date] 25/01/2022");
        label1.setBounds(0, 20, 200, 20);
        label1.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel label2 = new JLabel("[Version] 1.0.0");
        label2.setBounds(0, 40, 200, 20);
        label2.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(label);
        panel.add(label1);
        panel.add(label2);

        UIManager.put("OptionPane.minimumSize", new Dimension(300, 120));
        JOptionPane.showMessageDialog(null, panel, "Informazioni", JOptionPane.PLAIN_MESSAGE);
    }

}