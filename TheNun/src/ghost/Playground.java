package ghost;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.io.File;
import java.io.IOException;

public class Playground extends JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
    private Player player;
    private NPC nun;
    private Clip jumpscareSound;
    private Clip scream;
    private final int DELAY = 10;
    private boolean gameRunning = true;
    private boolean isJumpscareActive = false;
    private boolean isNunImageActive = false;
    private boolean showkindnunImage = false;
    private Timer kindnunTimer;
    private int kindnunDuration = 5000;
    private int shakeDuration = 2000;
    private long shakeStartTime = 0;
    private int shakeIntensity = 10;
    private int nunImageX = 0;
    private int nunImageY = 0;
    private Image bgimage;

    private Image jumpscareImage;
    private Image kindnunImage;

    private List<Item> items;

    private int score = 0;

    Playground(String location) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        bgimage = Toolkit.getDefaultToolkit().getImage(location);
        player = new Player("resources/player.png");
        nun = new NPC("resources/nun.png");
        kindnunImage = Toolkit.getDefaultToolkit().getImage("resources/kindnun.png");

        jumpscareImage = Toolkit.getDefaultToolkit().getImage("resources/jumpscare.gif");
        loadJumpscareSound();
        Toolkit.getDefaultToolkit().getImage("resources/nun.png");

        items = new ArrayList<>();
        items.add(new Bible("resources/bible.png", 300, 300));
        items.add(new Cross("resources/cross.png", 200, 200));
        items.add(new Cross("resources/cross.png", 400, 400));
        items.add(new Bible("resources/bible.png", 450, 500));
        items.add(new Cross("resources/cross.png", 500, 400));
        items.add(new Bible("resources/bible.png", 30, 300));
        items.add(new Cross("resources/BIBLE.png", 100, 500));
        items.add(new Cross("resources/cross.png", 130, 400));
        items.add(new Bible("resources/bible.png", 300, 700));
        items.add(new Cross("resources/cross.png", 100, 650));

        
        kindnunTimer = new Timer(kindnunDuration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showkindnunImage = false;
                kindnunTimer.stop();
                restartGame();
                openMenuWindow();
            }
        });

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadJumpscareSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/dead.wav"));
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new File("resources/scream.wav"));
            jumpscareSound = AudioSystem.getClip();
            jumpscareSound.open(audioInputStream);
            scream = AudioSystem.getClip();
            scream.open(audioInputStream1);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            refresh();
            checkCollisions();
        } else if (isJumpscareActive) {
            if ((System.currentTimeMillis() - shakeStartTime) >= shakeDuration) {
                restartGame();
                nun.resetPosition();
                nun.update1();
            }
        }
    }

    private void checkCollisions() {
        if (score == 10) {
            showkindnunImage = true;
            kindnunTimer.start();
        }

        if (player.getBounds().intersects(nun.getBounds())) {
            gameRunning = false;
            isJumpscareActive = true;
            isNunImageActive = true;
            shakeStartTime = System.currentTimeMillis();
            nunImageX = 0;
            nunImageY = 0;

            if (jumpscareSound != null && scream != null) {
                jumpscareSound.setFramePosition(0);
                jumpscareSound.start();
                scream.setFramePosition(0);
                scream.start();
            }

            score = 0;
        }

        for (Item item : items) {
            if (item.isVisible() && player.getBounds().intersects(item.getBounds())) {
                item.setVisible(false);
                score++;
            }
        }
    }

    private void restartGame() {
        player.setX(90);
        player.setY(90);
        gameRunning = true;
        isJumpscareActive = false;
        isNunImageActive = false;

        for (Item item : items) {
            item.setVisible(true);
        }
    }

    private boolean gameWindowOpen = false;

    private void openMenuWindow() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (!gameWindowOpen) {
                    Window parentWindow = SwingUtilities.windowForComponent(Playground.this);
                    if (parentWindow instanceof JFrame) {
                        parentWindow.dispose();
                        new GameWindow();
                        gameWindowOpen = true;
                    }
                }
            }
        });
    }

    private void refresh() {
        player.move();
        nun.update1();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
			draw(g);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Toolkit.getDefaultToolkit().sync();
    }

    private void draw(Graphics g) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Graphics2D g2d = (Graphics2D) g;

        if (bgimage != null) {
            g2d.drawImage(bgimage, 0, 0, getWidth(), getHeight(), this);
        }

        if (showkindnunImage) {
            int centerX = getWidth() / 2 - kindnunImage.getWidth(this) / 2;
            int centerY = getHeight() / 2 - kindnunImage.getHeight(this) / 2;
            g2d.drawImage(kindnunImage, centerX, centerY, this);

            try {
                
                AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(new File("resources/win.wav"));
                
                
                scream = AudioSystem.getClip();
                scream.open(audioInputStream2);
                scream.start();
                scream.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        scream.close();
                    }
                });

                
                audioInputStream2.close();

            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
            if (!isJumpscareActive) {
                g2d.drawImage(nun.getImage(), nun.getX(), nun.getY(), this);
            }

            for (Item item : items) {
                if (item.isVisible()) {
                    g.drawImage(item.getImage(), item.getX(), item.getY(), this);
                }
            }

            if (isNunImageActive) {
                int xOffset = nunImageX + (int) (Math.random() * shakeIntensity - (shakeIntensity / 2));
                int yOffset = nunImageY + (int) (Math.random() * shakeIntensity - (shakeIntensity / 2));
                g2d.drawImage(jumpscareImage, xOffset, yOffset, getWidth(), getHeight(), this);
            } else if (!isJumpscareActive) {
                g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
            }
        }

        Font font = new Font("Arial", Font.BOLD, 15);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("SCORE: " + score, 500, 40);
        g.drawString("COLLECT ALL THE ITEMS TO REMOVE THE CURSE", 30, 40);
    }

 
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
