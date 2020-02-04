package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener, Runnable {

    private JButton StartButton = new JButton();
    private JLabel nameLabel = new JLabel();
    private JFrame frame = new JFrame();
    private String[] object = {"O", "X"};
    private String[] first = {"Player", "Computer"};
    private JComboBox select = new JComboBox(object);
    private JComboBox select2 = new JComboBox(first);
    private Game game = new Game();
    private JButton retry = new JButton("Retry");
    private JLabel status = new JLabel();
    private Thread thread = null;

    Menu() {
        frame.setSize(320, 340);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Tic Tac Toe");
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.setSize(320, 340);
        game.setLocation(0, 0);
        game.setVisible(false);
        frame.add(game);

        nameLabel.setText("  GAME MENU");
        nameLabel.setBounds(120, 80, 100, 30);
        frame.add(nameLabel);

        select.setSelectedIndex(0);
        select.setBounds(110, 120, 100, 30);
        frame.add(select);
        select.addActionListener(this);

        select2.setSelectedIndex(0);
        select2.setBounds(110, 160, 100, 30);
        frame.add(select2);
        select2.addActionListener(this);

        StartButton.setText("Start");
        StartButton.setBounds(110, 200, 100, 30);
        frame.add(StartButton);
        StartButton.addActionListener(this);

        status.setText("  GAME MENU");
        status.setBounds(110, 120, 100, 30);
        frame.add(status);
        status.setVisible(false);

        retry.setBounds(110, 150, 100, 30);
        frame.add(retry);
        retry.addActionListener(this);
        retry.setVisible(false);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == StartButton) {
            if (select.getSelectedIndex() != 0) {
                game.setCircle(false);
            } else {
                game.setCircle(true);
            }

            if (select2.getSelectedIndex() != 0) {
                game.setFirst(false);
            } else {
                game.setFirst(true);
            }

            StartButton.setVisible(false);
            select.setVisible(false);
            select2.setVisible(false);
            nameLabel.setVisible(false);

            game.setVisible(true);
            game.runGame();

            thread = new Thread(this);
            thread.start();

        }

        if (source == retry) {

            retry.setVisible(false);
            status.setVisible(false);

            StartButton.setVisible(true);
            select.setVisible(true);
            select2.setVisible(true);
            nameLabel.setVisible(true);
        }

    }

    public void gameStatus() {
        while (true) {

            if (game.getStatus().equals(GameStatus.Player)) {
                status.setText("PLAYER WIN");
                game.setVisible(false);
                status.setVisible(true);
                retry.setVisible(true);
                return;
            }

            if (game.getStatus().equals(GameStatus.Computer)) {
                status.setText("COMPUTER WIN");
                game.setVisible(false);
                status.setVisible(true);
                retry.setVisible(true);
                return;
            }

            if (game.getStatus().equals(GameStatus.Full)) {
                status.setText("BBOARD IS FULL");
                game.setVisible(false);
                status.setVisible(true);
                retry.setVisible(true);
                return;
            }

            if (game.getStatus().equals(GameStatus.Exception)) {
                status.setText("UNNAME PRO8L3M");
                game.setVisible(false);
                status.setVisible(true);
                retry.setVisible(true);
                return;
            }

            game.updateGame();
            game.repaint();

        }
    }

    @Override
    public void run() {
        gameStatus();
    }
}