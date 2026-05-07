package view;

import model.Product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShoeStoreView extends JFrame {

    private static final Color WHITE = Color.WHITE;
    private static final Color TITLE_COLOR = new Color(70, 70, 70);
    private static final Color TEXT_COLOR = new Color(80, 80, 80);
    private static final Color MUTED_COLOR = new Color(150, 150, 150);
    private static final int DETAIL_WIDTH = 320;

    private JLabel mainImageLabel;
    private JLabel mainNameLabel;
    private JLabel mainPriceLabel;
    private JLabel mainBrandLabel;
    private JTextArea mainDescriptionArea;

    private JPanel productGrid;

    private final List<ProductCard> productCards = new ArrayList<>();

    public ShoeStoreView() {
        setTitle("Lab 3 - Shoe Store");
        setSize(1200, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(WHITE);
        rootPanel.setBorder(new EmptyBorder(14, 12, 14, 12));

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        rootPanel.add(leftPanel, BorderLayout.WEST);
        rootPanel.add(rightPanel, BorderLayout.CENTER);

        add(rootPanel);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(WHITE);
        leftPanel.setBorder(new EmptyBorder(96, 6, 10, 18));

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);
        imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imagePanel.setMaximumSize(new Dimension(300, 160));

        mainImageLabel = new JLabel();
        mainImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainImageLabel.setPreferredSize(new Dimension(300, 160));
        mainImageLabel.setMaximumSize(new Dimension(300, 160));
        imagePanel.add(mainImageLabel, BorderLayout.CENTER);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(210, 214, 220));
        separator.setPreferredSize(new Dimension(280, 2));

        JPanel separatorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        separatorPanel.setOpaque(false);
        separatorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        separatorPanel.setMaximumSize(new Dimension(300, 2));
        separatorPanel.add(separator);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.setMaximumSize(new Dimension(280, 180));

        mainNameLabel = createLabel(new Font("Arial", Font.BOLD, 22), TITLE_COLOR);
        mainPriceLabel = createLabel(new Font("Arial", Font.BOLD, 20), new Color(45, 45, 45));
        mainBrandLabel = createLabel(new Font("Arial", Font.PLAIN, 13), TEXT_COLOR);

        mainDescriptionArea = new JTextArea();
        mainDescriptionArea.setFont(new Font("Arial", Font.BOLD, 12));
        mainDescriptionArea.setForeground(MUTED_COLOR);
        mainDescriptionArea.setLineWrap(true);
        mainDescriptionArea.setWrapStyleWord(true);
        mainDescriptionArea.setEditable(false);
        mainDescriptionArea.setFocusable(false);
        mainDescriptionArea.setOpaque(false);
        mainDescriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainDescriptionArea.setMargin(new Insets(0, 0, 0, 0));
        mainDescriptionArea.setBorder(null);
        mainDescriptionArea.setPreferredSize(new Dimension(280, 90));
        mainDescriptionArea.setMaximumSize(new Dimension(280, 90));

        textPanel.add(mainNameLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(mainPriceLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(mainBrandLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(mainDescriptionArea);

        leftPanel.add(imagePanel);
        leftPanel.add(Box.createVerticalStrut(18));
        leftPanel.add(separatorPanel);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(textPanel);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(WHITE);
        container.setBorder(new EmptyBorder(72, 0, 0, 0));

        productGrid = new JPanel(new GridLayout(0, 4, 10, 10));
        productGrid.setBackground(WHITE);

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(WHITE);
        topWrapper.add(productGrid, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(topWrapper);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(WHITE);

        container.add(scrollPane, BorderLayout.CENTER);

        return container;
    }

    public void displayProducts(List<Product> products) {
        productGrid.removeAll();
        productCards.clear();

        for (Product product : products) {
            ProductCard card = new ProductCard(product);
            productCards.add(card);
            productGrid.add(card);
        }

        productGrid.revalidate();
        productGrid.repaint();
    }

    public void showProductDetail(Product product) {
        mainImageLabel.setIcon(loadImage(product.getImagePath(), 255, 135));
        mainNameLabel.setText(product.getName());
        mainPriceLabel.setText(product.getPrice());
        mainBrandLabel.setText(product.getBrand());
        mainDescriptionArea.setText(product.getDescription());

        // Làm mới nhẹ để nội dung đổi ngay sau khi chọn sản phẩm.
        revalidate();
        repaint();
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    private JLabel createLabel(Font font, Color color) {
        JLabel label = new JLabel();
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setPreferredSize(new Dimension(280, 32));
        label.setMinimumSize(new Dimension(280, 32));
        label.setMaximumSize(new Dimension(280, 32));
        return label;
    }

    private ImageIcon loadImage(String path, int maxWidth, int maxHeight) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() <= 0) {
            System.out.println("Khong tim thay anh: " + path);
            return new ImageIcon();
        }

        return new ImageIcon(scaleToFit(icon.getImage(), maxWidth, maxHeight));
    }

    // Giữ tỉ lệ gốc của ảnh để giày không bị kéo dãn.
    private Image scaleToFit(Image image, int maxWidth, int maxHeight) {
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double scale = Math.min((double) maxWidth / imageWidth, (double) maxHeight / imageHeight);

        int width = Math.max(1, (int) Math.round(imageWidth * scale));
        int height = Math.max(1, (int) Math.round(imageHeight * scale));

        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
