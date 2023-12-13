package ghost;

import java.awt.Rectangle;
import java.util.Random;

public class NPC extends Sprite {
    private Integer nun1_y = 500;
    private Integer nun1_x = 500;
    private Boolean nun1_left = true;
    private int windowWidth = 700;
    private int windowHeight = 800;

    NPC(String location) {
        super(location);
    }

    public void update1() {
        Random rand = new Random();

        if (rand.nextInt(100) < 2) {
            nun1_left = !nun1_left;
        }

        int speed = rand.nextInt(3) + 1;

        if (nun1_left) {
            nun1_x -= speed;
        } else {
            nun1_x += speed;
        }

        nun1_x = Math.max(0, Math.min(nun1_x, windowWidth - getWidth()));

        if (rand.nextInt(100) < 2) {
            nun1_y = rand.nextInt(windowHeight - getHeight());
        }

        this.setX(nun1_x);
        this.setY(nun1_y);
    }

    public Rectangle getBounds() {
        return new Rectangle(nun1_x - 40, nun1_y - 40, getWidth() - 40, getHeight() - 40);
    }

    public void resetPosition() {
        nun1_x = 500;
        nun1_y = 500;
        this.setX(nun1_x);
        this.setY(nun1_y);
    }
}
