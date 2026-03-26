import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class bai4 extends JPanel implements ActionListener, KeyListener{
    int boardWidth = 360; 
    int boardHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    //Bird
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    double velocityY = 0;
    double gravity = 0.5;
    double jumpStrength = -8;

    // Pipe
    int pipeWidth = 64;
    int pipeHeight = 512;
    int pipeSpeed = -4;
    int openingSpace = 150;

    ArrayList<Pipe> pipes = new ArrayList<>();
    Random random = new Random();

    // Game
    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    double score = 0;

    class Pipe {
        int x;
        int y;
        int width;
        int height;
        Image img;
        boolean passed = false;

        Pipe(Image img, int x, int y, int width, int height) {
            this.img = img;
            this.x = x; 
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    public bai4() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon("flappybirdbg.png").getImage();
        birdImage = new ImageIcon("flappybird.png").getImage();
        topPipeImage = new ImageIcon("toppipe.png").getImage();
        bottomPipeImage = new ImageIcon("bottompipe.png").getImage();

        // Tao cap ong dau tien
        placePipes();

        // Timer tao ong moi
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    placePipes();
                }
            }
        });
        placePipesTimer.start();

        // Game Loop
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void placePipes() {
        // Tao vi tri ngau nhien cho ong tren
        int randomPipeY = -pipeHeight / 4 - random.nextInt(pipeHeight / 2);

        Pipe topPipe = new Pipe(topPipeImage, boardWidth, randomPipeY, pipeWidth, pipeHeight);
        Pipe bottomPipe = new Pipe(bottomPipeImage, boardWidth, randomPipeY + pipeHeight + openingSpace, pipeWidth, pipeHeight);
        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void move() {
        // Chim roi xuong
        velocityY += gravity;
        birdY += (int) velocityY;

        // Dung mep tren hoac mep duoi => game over
        if (birdY < 0 || birdY + birdHeight > boardHeight) {
            gameOver = true;
        }

        // Di chuyen ong
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x += pipeSpeed;

            // Tinh diem: qua moi ong duoc 0.5 diem => qua 1 cap ong = 1 diem
            if (!pipe.passed && birdX > pipe.x + pipe.width) {
                score += 0.5;
                pipe.passed = true;
            }

            // Va cham voi ong => game over
            if (collision(pipe)) {
                gameOver = true;
            }
        }

        // XOa ong da ra khoi man hinh
        while (pipes.size() > 0 && pipes.get(0).x + pipes.get(0).width < 0) {
            pipes.remove(0);
        }
    }

    public boolean collision(Pipe pipe) {
        return birdX < pipe.x + pipe.width && 
               birdX + birdWidth > pipe.x &&
               birdY < pipe.y + pipe.height &&
               birdY + birdHeight > pipe.y;
    }

    public void restartGame() {
        birdY = boardHeight / 2;
        velocityY = 0;
        score = 0;
        gameOver = false;

        pipes.clear();
        placePipes();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Ve nen
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);

        // Ve ong
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        // Ve chim
        g.drawImage(birdImage, birdX, birdY, birdWidth, birdHeight, null);

        // Ve diem
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Score: " + (int) score, 20, 40);

        // Ve game over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("GAME OVER", 75, 280);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Nhan SPACE hoac ENTER de choi lai", 25, 320);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (gameOver) {
                restartGame(); // restart khi game over
            } else {
                velocityY = jumpStrength; // chim nhay len
            }
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
        bai4 gamePanel = new bai4();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.requestFocusInWindow();
    }

}
