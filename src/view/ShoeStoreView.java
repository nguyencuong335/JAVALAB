package view;

import model.Product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShoeStoreView extends JFrame {

    private JLabel mainImageLabel;
    private JLabel mainNameLabel;
    private JLabel mainPriceLabel;
    private JLabel mainBrandLabel;
    private JTextArea mainDescriptionArea;

    private JPanel productGrid;

    private List<ProductCard> productCards = new ArrayList<>();

    public ShoeStoreView() {
        setTitle("Lab 3 - Shoe Store");
        setSize(1180, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBackground(Color.WHITE);
        rootPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        rootPanel.add(leftPanel, BorderLayout.WEST);
        rootPanel.add(rightPanel, BorderLayout.CENTER);

        add(rootPanel);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(290, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(new EmptyBorder(10, 0, 10, 25));

        mainImageLabel = new JLabel();
        mainImageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainImageLabel.setPreferredSize(new Dimension(280, 190));
        mainImageLabel.setMaximumSize(new Dimension(280, 190));

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(280, 1));

        mainNameLabel = new JLabel();
        mainNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainNameLabel.setForeground(new Color(70, 70, 70));
        mainNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainPriceLabel = new JLabel();
        mainPriceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPriceLabel.setForeground(new Color(45, 45, 45));
        mainPriceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainBrandLabel = new JLabel();
        mainBrandLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        mainBrandLabel.setForeground(new Color(80, 80, 80));
        mainBrandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainDescriptionArea = new JTextArea();
        mainDescriptionArea.setFont(new Font("Arial", Font.BOLD, 14));
        mainDescriptionArea.setForeground(new Color(150, 150, 150));
        mainDescriptionArea.setLineWrap(true);
        mainDescriptionArea.setWrapStyleWord(true);
        mainDescriptionArea.setEditable(false);
        mainDescriptionArea.setFocusable(false);
        mainDescriptionArea.setOpaque(false);
        mainDescriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainDescriptionArea.setMaximumSize(new Dimension(280, 120));

        leftPanel.add(mainImageLabel);
        leftPanel.add(Box.createVerticalStrut(20));
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
        container.setBackground(Color.WHITE);

        productGrid = new JPanel(new GridLayout(0, 4, 10, 10));
        productGrid.setBackground(Color.WHITE);

        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.WHITE);
        topWrapper.add(productGrid, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(topWrapper);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.WHITE);

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
        mainImageLabel.setIcon(loadImage(product.getImagePath(), 280, 180));
        mainNameLabel.setText(product.getName());
        mainPriceLabel.setText(product.getPrice());
        mainBrandLabel.setText(product.getBrand());
        mainDescriptionArea.setText(product.getDescription());

        playSimpleEffect();
    }

    public List<ProductCard> getProductCards() {
        return productCards;
    }

    private void playSimpleEffect() {
        mainImageLabel.setVisible(false);
        mainNameLabel.setVisible(false);
        mainPriceLabel.setVisible(false);
        mainBrandLabel.setVisible(false);
        mainDescriptionArea.setVisible(false);

        Timer timer = new Timer(120, e -> {
            mainImageLabel.setVisible(true);
            mainNameLabel.setVisible(true);
            mainPriceLabel.setVisible(true);
            mainBrandLabel.setVisible(true);
            mainDescriptionArea.setVisible(true);
        });

        timer.setRepeats(false);
        timer.start();
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
}