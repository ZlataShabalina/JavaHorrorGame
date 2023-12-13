package ghost;

import java.awt.*;
import java.util.Random;

class Item {
    private Image image;
    private int x;
    private int y;
    private boolean visible;

    public Item(String imagePath, int x, int y) {
        this.image = Toolkit.getDefaultToolkit().getImage(imagePath);
        this.x = x;
        this.y = y;
        this.visible = true;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    
    public void setRandomPosition(int windowWidth, int windowHeight) {
        Random random = new Random();
        this.x = random.nextInt(windowWidth - image.getWidth(null));
        this.y = random.nextInt(windowHeight - image.getHeight(null));
    }
}

class Bible extends Item {
    public Bible(String imagePath, int x, int y) {
        super(imagePath, x, y);
    }
}

class Cross extends Item {
    public Cross(String imagePath, int x, int y) {
        super(imagePath, x, y);
    }
}
