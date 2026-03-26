import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class bai3 extends JPanel implements ActionListener, KeyListener{
    int boardWidth = 360; 
    int boardHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    // Bird
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    double velocityY = 0;
    double gravity = 0.5;
    double jumpStrength = -8;

    //Pipe
    int pipeX = boardWidth;
    int pipeWidth = 64;
    int topPipeHeight;
    int pipeGap = 150;
    int pipeSpeed = 4;

    Timer gameLoop;
    Random random = new Random();

    public bai3() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon("flappybirdbg.png").getImage();
        birdImage = new ImageIcon("flappybird.png").getImage();
        topPipeImage = new ImageIcon("toppipe.png").getImage();
        bottomPipeImage = new ImageIcon("bottompipe.png").getImage();

        randomizePipe();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void randomizePipe() {
        topPipeHeight = 50 + random.nextInt(221); // tu 120 den 340
        pipeX = boardWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Ve nen
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);

        // Tinh ong duoi
        int bottomPipeY = topPipeHeight + pipeGap;
        int bottomPipeHeight = boardHeight - bottomPipeY;

        // Ve ong tren
        g.drawImage(topPipeImage, pipeX, 0, pipeWidth, topPipeHeight, null);

        // Ve ong duoi
        g.drawImage(bottomPipeImage, pipeX, bottomPipeY, pipeWidth, bottomPipeHeight, null);

        // Ve chim
        g.drawImage(birdImage, birdX, birdY, birdWidth, birdHeight, null);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Chim roi
        velocityY += gravity;
        birdY += (int) velocityY;

        // Gioi han chim trong man hinh
        if (birdY < 0) {
            birdY = 0;
            velocityY = 0;
        }

        if (birdY + birdHeight > boardHeight) {
            birdY = boardHeight - birdHeight;
            velocityY = 0;
        }

        // Ong chay sang trai
        pipeX -= pipeSpeed;

        // Khi ong chay het man hinh thi tao lai
        if (pipeX + pipeWidth < 0) {
            randomizePipe();
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
        bai3 gamePanel = new bai3();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.requestFocusInWindow();
    }
}
