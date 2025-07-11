package spaceinvaders.core;

import spaceinvaders.model.*;
import spaceinvaders.patterns.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board extends JPanel implements Runnable {

    private final int BOARD_WIDTH = 500;
    private final int BOARD_HEIGHT = 500;
    private Thread animator;

    private GameManager gameManager;
    private AlienFactory alienFactory;
    private MovementStrategy movementStrategy;

    private Player p;
    private List<Alien> aliens;
    private List<Missile> missiles;

    public Board() {
        this.gameManager = GameManager.getInstance();
        this.alienFactory = new AlienFactory();
        this.movementStrategy = new StandardMovement();

        addKeyListener(new TAdapter());
        addMouseListener(new MAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);

        animator = new Thread(this);
        animator.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {
        p = new Player(BOARD_WIDTH / 2, BOARD_HEIGHT - 60, 5);
        missiles = new ArrayList<>();
        aliens = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int alienX = 10 + (i % 5) * 40;
            int alienY = 10 + (i / 5) * 40;
            Alien alien = alienFactory.createAlien("STANDARD", alienX, alienY);
            if (alien != null) {
                alien.moveRight = true;
                aliens.add(alien);
            }
        }

        gameManager.setIngame(false);
        gameManager.setMessage("Click Board to Start");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameManager.isIngame()) {
            drawGameObjects(g);
        } else {
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGameObjects(Graphics g) {
        updatePlayer(g);
        updateMissiles(g);
        updateAliens(g);
    }

    private void drawGameOver(Graphics g) {
        Font font = new Font("Helvetica", Font.BOLD, 24);
        FontMetrics metr = this.getFontMetrics(font);
        String message = gameManager.getMessage();
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2, BOARD_HEIGHT / 2);
    }

    private void updatePlayer(Graphics g) {
        if (p.moveRight && p.x < BOARD_WIDTH - 20) {
            p.x += p.speed;
        }
        if (p.moveLeft && p.x > 0) {
            p.x -= p.speed;
        }
        g.setColor(Color.red);
        g.fillRect(p.x, p.y, 20, 20);
    }

    private void updateAliens(Graphics g) {
        Iterator<Alien> it = aliens.iterator();
        while (it.hasNext()) {
            Alien alien = it.next();
            if (alien.isVisible()) {
                g.setColor(Color.green);
                g.fillRect(alien.x, alien.y, 30, 30);
            }
        }
    }

    private void updateMissiles(Graphics g) {
        Iterator<Missile> it = missiles.iterator();
        while (it.hasNext()) {
            Missile m = it.next();
            if (m.isVisible()) {
                g.setColor(Color.yellow);
                g.fillRect(m.getX(), m.getY(), m.getWidth(), m.getHeight());
            }
        }
    }

    public void fire() {
        missiles.add(new Missile(p.x + 10, p.y));
    }

    public void moveAliens() {
        movementStrategy.move(aliens, BOARD_WIDTH);
    }

    public void moveMissiles() {
        Iterator<Missile> it = missiles.iterator();
        while (it.hasNext()) {
            Missile m = it.next();
            if (m.isVisible()) {
                m.move();
            } else {
                it.remove();
            }
        }
    }

    public void checkCollisions() {
        Rectangle playerBounds = p.getBounds();

        for (Alien alien : aliens) {
            if (alien.isVisible() && playerBounds.intersects(alien.getBounds())) {
                gameManager.setIngame(false);
                gameManager.setMessage("Game Over");
                return;
            }
        }

        for (Missile m : missiles) {
            if (!m.isVisible()) continue;
            for (Alien alien : aliens) {
                if (alien.isVisible() && m.getBounds().intersects(alien.getBounds())) {
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        int animationDelay = 10;
        while (true) {
            if (gameManager.isIngame()) {
                moveAliens();
                moveMissiles();
                checkCollisions();
            }
            repaint();

            try {
                long timeDiff = System.currentTimeMillis() - beforeTime;
                long sleep = animationDelay - timeDiff;
                if (sleep < 0) sleep = 2;
                Thread.sleep(sleep);
                beforeTime = System.currentTimeMillis();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) p.moveLeft = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) p.moveRight = true;
            if (e.getKeyCode() == KeyEvent.VK_SPACE && gameManager.isIngame()) {
                fire();
            }
        }
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) p.moveLeft = false;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) p.moveRight = false;
        }
    }

    private class MAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (!gameManager.isIngame()) {
                gameInit();
                gameManager.setIngame(true);
            }
        }
    }
}