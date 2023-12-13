package ghost;


import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePlayWindow extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    
   
   

    GamePlayWindow() {
        add(new Playground("resources/inside.jpeg"));

        setTitle("THE NUN");
        setSize(605, 829);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);  
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
