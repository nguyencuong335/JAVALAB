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
    private static final int DETAIL_WIDTH = 300;

    private JLabel mainImageLabel;
    private JLabel mainNameLabel;
    private JLabel mainPriceLabel;
    private JLabel mainBrandLabel;
    private JTextArea mainDescriptionArea;

    private JPanel productGrid;

    private final List<ProductCard> productCards = new ArrayList<>();

    public ShoeStoreView() {
        setTitle("Lab 3 - Shoe Store");
        setSize(1180, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(WHITE);
        rootPanel.setBorder(new EmptyBorder(30, 18, 18, 18));

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        rootPanel.add(leftPanel, BorderLayout.WEST);
        rootPanel.add(rightPanel, BorderLayout.CENTER);

        add(rootPanel);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(DETAIL_WIDTH, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(WHITE);
        leftPanel.setBorder(new EmptyBorder(55, 0, 10, 26));

        mainImageLabel = new JLabel();
        mainImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainImageLabel.setPreferredSize(new Dimension(DETAIL_WIDTH, 180));
        mainImageLabel.setMaximumSize(new Dimension(DETAIL_WIDTH, 180));

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(210, 214, 220));
        separator.setMaximumSize(new Dimension(DETAIL_WIDTH, 1));

        mainNameLabel = createLabel(new Font("Arial", Font.BOLD, 22), TITLE_COLOR);
        mainPriceLabel = createLabel(new Font("Arial", Font.BOLD, 20), new Color(45, 45, 45));
        mainBrandLabel = createLabel(new Font("Arial", Font.PLAIN, 13), TEXT_COLOR);

        mainDescriptionArea = new JTextArea();
        mainDescriptionArea.setFont(new Font("Arial", Font.BOLD, 14));
        mainDescriptionArea.setForeground(MUTED_COLOR);
        mainDescriptionArea.setLineWrap(true);
        mainDescriptionArea.setWrapStyleWord(true);
        mainDescriptionArea.setEditable(false);
        mainDescriptionArea.setFocusable(false);
        mainDescriptionArea.setOpaque(false);
        mainDescriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainDescriptionArea.setMaximumSize(new Dimension(DETAIL_WIDTH, 120));

        leftPanel.add(mainImageLabel);
        leftPanel.add(Box.createVerticalStrut(28));
        leftPanel.add(separator);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(mainNameLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(mainPriceLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(mainBrandLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(mainDescriptionArea);

        return leftPanel;
    }

    private JPanel createRightPanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(WHITE);
        container.setBorder(new EmptyBorder(55, 0, 0, 0));

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
        mainImageLabel.setIcon(loadImage(product.getImagePath(), 240, 145));
        mainNameLabel.setText(product.getName());
        mainPriceLabel.setText(product.getPrice());
        mainBrandLabel.setText(product.getBrand());
        mainDescriptionArea.setText(product.getDescription());

        // Làm mới nhẹ để nội dung đổi ngay sau khi chọn sản phẩm.
        revalidate();
        repaint();
    }

    private ImageIcon loadImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() <= 0) {
            System.out.println("Không tìm thấy ảnh: " + path);
            return new ImageIcon();
        }

        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    private JLabel createLabel(Font font, Color color) {
        JLabel label = new JLabel();
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}
