package ghost;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

    private int x = 90;
    private int y = 90;
    private int w;
    private int h;
    private Image image;
    float alpha = 1.0f;
    
    Sprite(String location){
    	loadSprite(location);
    }
    
    
    public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	private void loadSprite(String location) {
    	ImageIcon sprite = new ImageIcon(location);
        image = sprite.getImage(); 
        
        w = image.getWidth(null);
        h = image.getHeight(null);
    }
    
    public int getX() { 
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
    public int getWidth() {
        return w;
    }
    
    public int getHeight() {
        return h;
    }    
    
    public Image getImage() {
        return image;
    }

}
