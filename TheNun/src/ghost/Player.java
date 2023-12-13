package ghost;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class Player extends Sprite {
    private int dx;
    private int dy;

    Player(String location) {
        super(location);
        setAlpha(0.6f);
    }

    public void move() {
        this.setX(dx + this.getX());
        this.setY(dy + this.getY());
    }

    
    public Rectangle getBounds() {
        return new Rectangle(getX() - 40, getY() - 40, getWidth() - 40, getHeight() - 40);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            dx = -4;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            dx = 4;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            dy = -4;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            dy = 4;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
