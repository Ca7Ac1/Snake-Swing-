package SnakeGame.SnakeBoard.SnakeAssets;

import java.util.Random;

import SnakeGame.SnakeBoard.Board;

public class Food {

    private int foodX;
    private int foodY;

    private Random random;
    private final int RANDOMPOSITION = Math.min(Board.BOARDHEIGHT, Board.BOARDWIDTH) / Board.PIXELSIZE;

    int locationX;
    int locationY;

    int specificX;
    int specificY;

    public Food() {
        random = new Random();
    }

    public void createFood() {
        locationX = random.nextInt(RANDOMPOSITION);
        locationY = random.nextInt(RANDOMPOSITION);

        foodX = locationX * Board.getDotSize();
        foodY = locationY * Board.getDotSize();

        if ((foodX == Board.snake.getSnakeX(0)) && (foodY == Board.snake.getSnakeY(0))) {
            createFood();
        }
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

}