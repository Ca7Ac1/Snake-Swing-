package SnakeGame.SnakeBoard.SnakeAssets;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import SnakeGame.SnakeBoard.Board;

public class KeyChecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!Board.snake.isMovingRight()) && (!Board.keyPressed)) {
            Board.snake.setMovingLeft();
            Board.keyPressed = true;
        }

        if ((key == KeyEvent.VK_RIGHT) && (!Board.snake.isMovingLeft()) && (!Board.keyPressed)) {
            Board.snake.setMovingRight();
            Board.keyPressed = true;
        }

        if ((key == KeyEvent.VK_UP) && (!Board.snake.isMovingDown()) && (!Board.keyPressed)) {
            Board.snake.setMovingUp();
            Board.keyPressed = true;
        }

        if ((key == KeyEvent.VK_DOWN) && (!Board.snake.isMovingUp()) && (!Board.keyPressed)) {
            Board.snake.setMovingDown();
            Board.keyPressed = true;
        }
    }
}