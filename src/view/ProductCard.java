package view;

import model.Product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ProductCard extends JPanel {

    private static final Color CARD_COLOR = new Color(244, 244, 244);
    private static final Color HOVER_COLOR = new Color(236, 236, 236);
    private static final Color SELECTED_BORDER = new Color(70, 130, 255);
    private static final Color TITLE_COLOR = new Color(75, 75, 75);
    private static final Color SUBTLE_COLOR = new Color(170, 170, 170);
    private static final Color TEXT_COLOR = new Color(70, 70, 70);
    private static final int CARD_WIDTH = 200;
    private static final int CARD_HEIGHT = 238;

    private boolean selected;

    private final Product product;

    public ProductCard(Product product) {
        this.product = product;

        setLayout(new BorderLayout(0, 12));
        setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setMinimumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setMaximumSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));

        setCardBorder(CARD_COLOR);

        createCardUI();
    }

    private void createCardUI() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setPreferredSize(new Dimension(182, 54));
        textPanel.setMinimumSize(new Dimension(182, 54));
        textPanel.setMaximumSize(new Dimension(182, 54));

        JLabel nameLabel = createTextLabel(shortenText(product.getName(), 16), new Font("Arial", Font.BOLD, 16), TITLE_COLOR);
        nameLabel.setPreferredSize(new Dimension(182, 24));
        nameLabel.setMinimumSize(new Dimension(182, 24));
        nameLabel.setMaximumSize(new Dimension(182, 24));

        JLabel descLabel = createTextLabel(shortenText(product.getDescription(), 25), new Font("Arial", Font.BOLD, 12), SUBTLE_COLOR);
        descLabel.setPreferredSize(new Dimension(182, 20));
        descLabel.setMinimumSize(new Dimension(182, 20));
        descLabel.setMaximumSize(new Dimension(182, 20));

        textPanel.add(nameLabel);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(descLabel);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(182, 110));
        imageLabel.setMinimumSize(new Dimension(182, 110));
        imageLabel.setMaximumSize(new Dimension(182, 110));
        imageLabel.setIcon(loadImage(product.getImagePath(), 150, 95));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(182, 35));
        bottomPanel.setMinimumSize(new Dimension(182, 35));
        bottomPanel.setMaximumSize(new Dimension(182, 35));

        JLabel brandLabel = createTextLabel(product.getBrand(), new Font("Arial", Font.PLAIN, 12), TEXT_COLOR);

        JLabel priceLabel = createTextLabel(product.getPrice(), new Font("Arial", Font.BOLD, 19), new Color(65, 65, 65));

        bottomPanel.add(brandLabel, BorderLayout.WEST);
        bottomPanel.add(priceLabel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(imagePanel, BorderLayout.SOUTH);

        add(textPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public Product getProduct() {
        return product;
    }

    public void setSelectedStyle() {
        selected = true;
        setCardBorder(SELECTED_BORDER);
        repaint();
    }

    public void setDefaultStyle() {
        selected = false;
        setCardBorder(CARD_COLOR);
        repaint();
    }

    public void setHoverStyle() {
        if (!selected) {
            setCardBorder(HOVER_COLOR);
        }
        repaint();
    }

    private ImageIcon loadImage(String path, int maxWidth, int maxHeight) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() <= 0) {
            System.out.println("Khong tim thay anh: " + path);
            return new ImageIcon();
        }

        return new ImageIcon(scaleToFit(icon.getImage(), maxWidth, maxHeight));
    }

    private String shortenText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength) + "...";
    }

    private JLabel createTextLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // Giữ tỉ lệ ảnh để giày trong card không bị nén theo khung.
    private Image scaleToFit(Image image, int maxWidth, int maxHeight) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double scale = Math.min((double) maxWidth / imageWidth, (double) maxHeight / imageHeight);

        int width = Math.max(1, (int) Math.round(imageWidth * scale));
        int height = Math.max(1, (int) Math.round(imageHeight * scale));

        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    // Dùng chung một kiểu viền để card đồng nhất và code ngắn hơn.
    private void setCardBorder(Color borderColor) {
        setBorder(new CompoundBorder(
                new LineBorder(borderColor, 1, true),
                new EmptyBorder(12, 10, 10, 10)
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(selected ? new Color(250, 250, 250) : CARD_COLOR);

        // Tô nền bo góc để card giống mockup hơn.
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
        g2.dispose();
    }
}
