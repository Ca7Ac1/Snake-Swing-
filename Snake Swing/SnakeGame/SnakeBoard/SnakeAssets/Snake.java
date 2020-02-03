package SnakeGame.SnakeBoard.SnakeAssets;

import SnakeGame.SnakeBoard.Board;

public class Snake {

    private final int[] x;
    private final int[] y;

    private int parts;

    public enum Direction {
        RIGHT, LEFT, UP, DOWN;
    }

    Direction direction;

    public Snake() {
        x = new int[Board.getAllDots()];
        y = new int[Board.getAllDots()];

        parts = 3;

        for (int i = 0; i < 3; i++) {
            x[i] = (Board.BOARDWIDTH / 2) + i;
            y[i] = (Board.BOARDHEIGHT / 2);
        }

        direction = Direction.RIGHT;
    }

    public int getSnakeX(int index) {
        return x[index];
    }

    public int getSnakeY(int index) {
        return y[index];
    }

    public void setSnakeX(int i) {
        x[0] = i;
    }

    public void setSnakeY(int i) {
        x[0] = i;
    }

    public boolean isMovingLeft() {
        return direction == Direction.LEFT;
    }

    public void setMovingLeft() {
        direction = Direction.LEFT;
    }

    public boolean isMovingRight() {
        return direction == Direction.RIGHT;
    }

    public void setMovingRight() {
        direction = Direction.RIGHT;
    }

    public boolean isMovingUp() {
        return direction == Direction.UP;
    }

    public void setMovingUp() {
        direction = Direction.UP;
    }

    public boolean isMovingDown() {
        return direction == Direction.DOWN;
    }

    public void setMovingDown() {
        direction = Direction.DOWN;
    }

    public int getParts() {
        return parts;
    }

    public void setParts(int x) {
        parts = x;
    }

    public void move() {
        for (int i = parts; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if (isMovingLeft()) {
            x[0] -= Board.getDotSize();
        }

        if (isMovingRight()) {
            x[0] += Board.getDotSize();
        }

        if (isMovingUp()) {
            y[0] -= Board.getDotSize();
        }

        if (isMovingDown()) {
            y[0] += Board.getDotSize();
        }
    }
}