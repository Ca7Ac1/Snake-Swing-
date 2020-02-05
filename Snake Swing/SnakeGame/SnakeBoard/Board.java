package SnakeGame.SnakeBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import SnakeGame.SnakeBoard.SnakeAssets.KeyChecker;
import SnakeGame.SnakeBoard.SnakeAssets.Snake;
import SnakeGame.Game;
import SnakeGame.SnakeBoard.SnakeAssets.Food;

public class Board extends JPanel implements ActionListener {

    public final static int BOARDWIDTH = 400;
    public final static int BOARDHEIGHT = 400;

    public final static int PIXELSIZE = 25;

    private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT) / (PIXELSIZE * PIXELSIZE);

    private boolean inGame;
    private boolean restart;

    private Timer timer;

    public static Snake snake;
    public static Food food;

    public static boolean keyPressed;

    public Board() {
        inGame = true;

        snake = new Snake();
        food = new Food();

        keyPressed = false;

        addKeyListener(new KeyChecker());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

        initializeGame();
    }

    private void initializeGame() {
        int speed = 100 - Integer.parseInt(JOptionPane.showInputDialog(this, "choose your difficulty(0 - 100)", "snake",
                JOptionPane.INFORMATION_MESSAGE));

        food.createFood();

        timer = new Timer(speed, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
        drawGrid(g);
    }

    private void draw(Graphics g) {
        if (inGame == true) {
            g.setColor(Color.RED);
            g.fillRect(food.getFoodX(), food.getFoodY(), PIXELSIZE, PIXELSIZE);

            for (int i = 0; i < snake.getParts(); i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                }

                g.fillRect(snake.getSnakeX(i), snake.getSnakeY(i), PIXELSIZE, PIXELSIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        } else {
            endGame();
        }
    }

    private void drawGrid(Graphics g) {
        for (int i = 0; i <= BOARDWIDTH + 1; i += PIXELSIZE) {
            for (int h = 0; h <= BOARDHEIGHT + 1; h += PIXELSIZE) {
                g.setColor(Color.WHITE);
                g.drawRect(i, h, PIXELSIZE, PIXELSIZE);
            }
        }
    }

    private void checkFoodCollisions() {
        for (int i = 0; i < snake.getParts(); i++) {
            if ((proximity(snake.getSnakeX(i), food.getFoodX(), 20))
                    && proximity(snake.getSnakeY(i), food.getFoodY(), 20)) {
                if (i == 0) {
                    snake.setParts(snake.getParts() + 1);
                    food.createFood();
                } else {
                    food.createFood();
                }
            }
        }
    }

    private void checkCollisions() {
        for (int i = snake.getParts(); i > 0; i--) {
            if ((snake.getSnakeX(0) == snake.getSnakeX(i)) && (snake.getSnakeY(0) == snake.getSnakeY(i))) {
                inGame = false;
            }
        }

        if ((snake.getSnakeY(0) >= BOARDHEIGHT) || (snake.getSnakeY(0) < 0) || (snake.getSnakeX(0) >= BOARDHEIGHT)
                || (snake.getSnakeX(0) < 0)) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }

    private void endGame() {
        if (JOptionPane.showConfirmDialog(null, "Game Over. Would you like to restart") == JOptionPane.YES_OPTION) {
            Game.restart();
        } else {
            Game.end();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame == true) {
            checkFoodCollisions();
            checkCollisions();
            snake.move();

            keyPressed = false;
        }

        repaint();
    }

    private boolean proximity(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }

    public static int getAllDots() {
        return TOTALPIXELS;
    }

    public static int getDotSize() {
        return PIXELSIZE;
    }
}