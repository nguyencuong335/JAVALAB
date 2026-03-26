import javax.swing.*;
import java.awt.*;

public class bai1 extends JPanel{
    private Image backgroundImage;
    public bai1() {
        backgroundImage = new ImageIcon("flappybird.png").getImage();
        setPreferredSize(new Dimension(360, 640));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, 360, 640, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        bai1 gamePanel = new bai1();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
