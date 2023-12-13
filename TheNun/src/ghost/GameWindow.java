package ghost;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage background;
 
    private Clip backgroundClip;

    

    public GameWindow() {
        try {
            background = ImageIO.read(new File("resources/menu.jpg"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(background.getWidth(), background.getHeight());
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopBackgroundMusic();
            }
        });

        playBackgroundMusic();

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("THE NUN");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);

        JButton startButton = new JButton("   Start   ");
        styleButton(startButton);

        JButton quitButton = new JButton("   Quit   ");
        styleButton(quitButton);

        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.gridx = 0;
        gbcTitle.gridy = 0;
        gbcTitle.gridwidth = 2;
        gbcTitle.insets = new Insets(230, 0, 0, 0);

        GridBagConstraints gbcStart = new GridBagConstraints();
        gbcStart.gridx = 0;
        gbcStart.gridy = 1;
        gbcStart.insets = new Insets(30, 10, 0, 0);

        GridBagConstraints gbcQuit = new GridBagConstraints();
        gbcQuit.gridx = 1;
        gbcQuit.gridy = 1;
        gbcQuit.insets = new Insets(30, 20, 0, 0);

        buttonPanel.add(titleLabel, gbcTitle);
        buttonPanel.add(startButton, gbcStart);
        buttonPanel.add(quitButton, gbcQuit);

        setContentPane(new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, this);
                
            }
        });

        add(buttonPanel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GamePlayWindow();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        

        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Serif", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.DARK_GRAY);
            }
        });
    }

    private void playBackgroundMusic() {
        try {
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(AudioSystem.getAudioInputStream(new File("resources/menu.wav")));
            backgroundClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }

    

}
