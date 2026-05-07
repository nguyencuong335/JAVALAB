package view;

import model.Product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ProductCard extends JPanel {

    private Product product;

    public ProductCard(Product product) {
        this.product = product;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(242, 242, 242));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(200, 238));
        setMinimumSize(new Dimension(200, 238));
        setMaximumSize(new Dimension(200, 238));

        setBorder(new CompoundBorder(
                new LineBorder(new Color(242, 242, 242), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        createCardUI();
    }

    private void createCardUI() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.setPreferredSize(new Dimension(180, 48));
        textPanel.setMinimumSize(new Dimension(180, 48));
        textPanel.setMaximumSize(new Dimension(180, 48));

        JLabel nameLabel = new JLabel(shortenText(product.getName(), 18));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        nameLabel.setForeground(new Color(75, 75, 75));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setPreferredSize(new Dimension(180, 24));
        nameLabel.setMinimumSize(new Dimension(180, 24));
        nameLabel.setMaximumSize(new Dimension(180, 24));

        JLabel descLabel = new JLabel(shortenText(product.getDescription(), 25));
        descLabel.setFont(new Font("Arial", Font.BOLD, 12));
        descLabel.setForeground(new Color(170, 170, 170));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        descLabel.setPreferredSize(new Dimension(180, 20));
        descLabel.setMinimumSize(new Dimension(180, 20));
        descLabel.setMaximumSize(new Dimension(180, 20));

        textPanel.add(nameLabel);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(descLabel);

        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(180, 100));
        imageLabel.setMinimumSize(new Dimension(180, 100));
        imageLabel.setMaximumSize(new Dimension(180, 100));
        imageLabel.setIcon(loadImage(product.getImagePath(), 165, 100));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomPanel.setPreferredSize(new Dimension(180, 35));
        bottomPanel.setMinimumSize(new Dimension(180, 35));
        bottomPanel.setMaximumSize(new Dimension(180, 35));

        JLabel brandLabel = new JLabel(product.getBrand());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        brandLabel.setForeground(new Color(70, 70, 70));

        JLabel priceLabel = new JLabel(product.getPrice());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 19));
        priceLabel.setForeground(new Color(65, 65, 65));

        bottomPanel.add(brandLabel, BorderLayout.WEST);
        bottomPanel.add(priceLabel, BorderLayout.EAST);

        add(textPanel);
        add(Box.createVerticalStrut(18));
        add(imageLabel);
        add(Box.createVerticalGlue());
        add(bottomPanel);
    }

    public Product getProduct() {
        return product;
    }

    public void setSelectedStyle() {
        setBackground(new Color(248, 248, 248));
        setBorder(new CompoundBorder(
                new LineBorder(new Color(70, 130, 255), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
    }

    public void setDefaultStyle() {
        setBackground(new Color(242, 242, 242));
        setBorder(new CompoundBorder(
                new LineBorder(new Color(242, 242, 242), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
    }

    public void setHoverStyle() {
        setBackground(new Color(232, 232, 232));
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

    private String shortenText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength) + "...";
    }
}