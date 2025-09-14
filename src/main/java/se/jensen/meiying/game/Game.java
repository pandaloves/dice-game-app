package se.jensen.meiying.game;

import javax.swing.JOptionPane;

public class Game {
    private Player player1;
    private Player player2;
    private Dice dice;
    private boolean singlePlayer;

    public Game() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.dice = new Dice();
        this.singlePlayer = false;
    }

    private void inputPlayerName(Player player, String playerNumber) {
        boolean validInput = false;
        while (!validInput) {
            try {
                String firstname = JOptionPane.showInputDialog("Enter first name for Player " + playerNumber + ":");
                player.setFirstname(firstname);
                String lastname = JOptionPane.showInputDialog("Enter last name for Player " + playerNumber + ":");
                player.setLastname(lastname);
                validInput = true;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + ". Please try again.");
            }
        }
    }

    private void setupPlayers() {
        String[] options = {"human vs computer", "human vs human"};
        int choice = JOptionPane.showOptionDialog(null, "Which game mode do you choose?", "Select a game mode.",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        if (choice == 0) {
            singlePlayer = true;
            inputPlayerName(player1, "1");
            try {
                player2.setFirstname("Computer");
                player2.setLastname("AI");
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
        } else {
            singlePlayer = false;
            inputPlayerName(player1, "1");
            inputPlayerName(player2, "2");
        }
    }

    private void rollDice() {
        int roll1 = dice.roll();
        player1.addToScore(roll1);
        JOptionPane.showMessageDialog(null, player1.getFullName() + " rolled " + roll1 + " on first roll.");
        int roll2 = dice.roll();
        player1.addToScore(roll2);
        JOptionPane.showMessageDialog(null, player1.getFullName() + " rolled " + roll2 + " on second roll.");
        JOptionPane.showMessageDialog(null, player1.getFullName() + "'s total score: " + player1.getScore());

        int roll3 = dice.roll();
        player2.addToScore(roll3);
        JOptionPane.showMessageDialog(null, player2.getFullName() + " rolled " + roll3 + " on first roll.");
        int roll4 = dice.roll();
        player2.addToScore(roll4);
        JOptionPane.showMessageDialog(null, player2.getFullName() + " rolled " + roll4 + " on second roll.");
        JOptionPane.showMessageDialog(null, player2.getFullName() + "'s total score: " + player2.getScore());

        if (player1.getScore() > player2.getScore()) {
            JOptionPane.showMessageDialog(null, player1.getFullName() + " wins with " + player1.getScore() + " points!");
        } else if (player2.getScore() > player1.getScore()) {
            JOptionPane.showMessageDialog(null, player2.getFullName() + " wins with " + player2.getScore() + " points!");
        } else {
            JOptionPane.showMessageDialog(null, "It's a tie! Both scored " + player1.getScore() + " points.");
        }

        player1.addToScore(-player1.getScore());
        player2.addToScore(-player2.getScore());
    }

    public void start() {
        setupPlayers();

        while (true) {
            String choice = JOptionPane.showInputDialog("Enter 'play' to start a match or 'quit' to exit:");
            if (choice == null || choice.equalsIgnoreCase("quit")) {
                JOptionPane.showMessageDialog(null, "Game ended.");
                break;
            } else if (choice.equalsIgnoreCase("play")) {
                rollDice();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Enter 'play' or 'quit'.");
            }
        }
    }
}
