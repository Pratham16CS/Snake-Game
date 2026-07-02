import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Page1 extends JFrame implements ActionListener {
    JButton b1 = new JButton("Start Game");
    JButton b2 = new JButton("Help");

    Page1() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 700));
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        b1.setBackground(Color.ORANGE);
        b1.setBounds(290, 180, 120, 50);
        b1.setFocusable(true);
        b1.addActionListener(this);

        b2.setBounds(300, 300, 100, 50);
        b2.setBackground(Color.ORANGE);
        b2.setFocusable(true);
        b2.addActionListener(this);

        panel.add(b1);
        panel.add(b2);
        this.add(panel);
        
        this.setTitle("Snake Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true); // Moved to the end of construction
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            this.dispose();
            new Gameframe();
        } else if (e.getSource() == b2) {
            // Added a helpful popup rule dialog
            JOptionPane.showMessageDialog(this, 
                "Use Arrow Keys to Navigate.\nEat Red Apples to Grow.\nDon't hit walls or yourself!", 
                "How to Play", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}