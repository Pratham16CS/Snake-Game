import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Gamepanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 700;
    static final int SCREEN_HEIGHT = 700;
    static final int CELL_SIZE = 25;
    static final int GAME_SIZE = (SCREEN_HEIGHT * SCREEN_WIDTH) / CELL_SIZE; 
    static int delay = 100;
    
    final int x[] = new int[GAME_SIZE];
    final int y[] = new int[GAME_SIZE];
    int bodyparts = 6;
    int foodX;
    int foodY;
    int foodeaten = 0;
    int highscore = 0;
    Timer timer;
    Random random;
    char direction = 'R';
    boolean running = false;
    JButton b1 = new JButton("Play Again");

    Gamepanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(100, 0, 4));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        
        this.setLayout(null); // Explicit layout management for absolute boundaries
        b1.setBounds(290, 460, 120, 50); 
        b1.setBackground(Color.lightGray);
        b1.setFocusable(false); // CRITICAL: Prevents button from stealing arrow-key focus
        b1.addActionListener(this);
        
        startGame();
    }

    public void startGame() {
        // Clear old snake trailing coordinates
        for (int i = 0; i < bodyparts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        bodyparts = 6;
        foodeaten = 0;
        direction = 'R';
        newFood();
        running = true;
        
        if (timer == null) {
            timer = new Timer(delay, this);
        }
        timer.start();
    }

    public void move() {
        for (int i = bodyparts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'R' -> x[0] = x[0] + CELL_SIZE;
            case 'L' -> x[0] = x[0] - CELL_SIZE;
            case 'U' -> y[0] = y[0] - CELL_SIZE;
            case 'D' -> y[0] = y[0] + CELL_SIZE;
        }
    }

    public void checkFood() {
        if (x[0] == foodX && y[0] == foodY) {
            bodyparts++;
            foodeaten++;
            newFood();
        }
    }

    public void newFood() {
        foodX = random.nextInt((int) SCREEN_WIDTH / CELL_SIZE) * CELL_SIZE;
        foodY = random.nextInt((int) (SCREEN_HEIGHT / CELL_SIZE)) * CELL_SIZE;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.RED);
            g.fillOval(foodX, foodY, CELL_SIZE, CELL_SIZE);
            
            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(new Color(2, 180, 1));
                }
                g.fillRect(x[i], y[i], CELL_SIZE, CELL_SIZE);
            }
            g.setColor(Color.CYAN);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Score:" + foodeaten, (SCREEN_WIDTH - metrics2.stringWidth("Score:" + foodeaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void checkCollision() {
        for (int i = bodyparts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // Fixed boundary calculations (>= instead of >)
        if (x[0] >= SCREEN_WIDTH || x[0] < 0 || y[0] >= SCREEN_HEIGHT || y[0] < 0) {
            running = false;
        }
        if (!running) {
            timer.stop();
        }
    }

    public int highScore() {
        if (foodeaten > highscore) {
            highscore = foodeaten;
        }
        return highscore;
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over!!!", (SCREEN_WIDTH - metrics1.stringWidth("Game Over!!!")) / 2, SCREEN_HEIGHT / 2);
        
        g.setColor(Color.CYAN);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("SCORE:" + foodeaten, (SCREEN_WIDTH - metrics2.stringWidth("SCORE:" + foodeaten)) / 2, SCREEN_HEIGHT / 3);
        
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("HIGH SCORE: " + highscore, (SCREEN_WIDTH - metrics3.stringWidth("HIGH SCORE: " + highscore)) / 2, (int)(SCREEN_HEIGHT / 1.5));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Intercept Button Taps and isolate them safely from the Timer
        if (e.getSource() == b1) {
            this.remove(b1);
            startGame();
            this.requestFocusInWindow(); // Returns keyboard focus to the game screen grid
            repaint();
            return;
        }

        if (running) {
            move();
            checkFood();
            checkCollision();
        }
        
        if (!running) {
            highScore();
            this.add(b1);
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> { if (direction != 'R') direction = 'L'; }
                case KeyEvent.VK_RIGHT -> { if (direction != 'L') direction = 'R'; }
                case KeyEvent.VK_UP -> { if (direction != 'D') direction = 'U'; }
                case KeyEvent.VK_DOWN -> { if (direction != 'U') direction = 'D'; }
            }
        }
    }
}