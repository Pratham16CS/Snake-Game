import javax.swing.*;

public class Gameframe extends JFrame {
    Gameframe(){
        Gamepanel p = new Gamepanel();
        this.add(p);
        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true); 
    }
}