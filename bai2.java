import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class bai2 extends JPanel implements ActionListener, KeyListener {
    
    // Kich thuoc cua so
    int boardWidth = 360; 
    int boardHeight = 640;

    // Anh
    Image backgroundImage;
    Image birdImage;

    // Bird
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    double velocityY = 0;
    double gravity = 0.5;
    double jumpStrength = -8;

    // Timer
    Timer gameLoop;

    public bai2() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon("flappybirdbg.png").getImage();
        birdImage = new ImageIcon("flappybird.png").getImage();

        gameLoop = new Timer(1000 / 60, this); // 60 FPS
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Ve nen
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);

        // Ve chim
        g.drawImage(birdImage, birdX, birdY, birdWidth, birdHeight, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Chim roi xuong
        velocityY += gravity;
        birdY += (int) velocityY;

        // Khong cho chim bay khoi mep tren
        if (birdY < 0) {
            birdY = 0;
            velocityY = 0;
        }

        // Khong cho chim roi khoi mep duoi
        if (birdY + birdHeight > boardHeight) {
            birdY = boardHeight - birdHeight;
            velocityY = 0;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            velocityY = jumpStrength; // Chim nhay len
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        bai2 gamePanel = new bai2();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.requestFocusInWindow(); // Dat tam focus vao panel de nhan su kien ban phim
    }
}

