package SnakeGame;

import java.awt.EventQueue;

import javax.swing.JFrame;

import SnakeGame.SnakeBoard.Board;

public class Game extends JFrame {

    private Board board;

    public Game() {
        board = new Board();

        add(board);
        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame frame = new Game();
                frame.setVisible(true);
            }
        });
    }
}