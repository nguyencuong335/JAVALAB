package controller;

import model.Product;
import view.ProductCard;
import view.ShoeStoreView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class ShoeStoreController {

    private final ShoeStoreView view;
    private final List<Product> products;

    private ProductCard selectedCard;

    public ShoeStoreController(ShoeStoreView view) {
        this.view = view;
        this.products = createProducts();

        view.displayProducts(products);

        if (!products.isEmpty()) {
            view.showProductDetail(products.get(0));
        }

        addEvents();
    }

    private List<Product> createProducts() {
        // Dữ liệu mẫu để render giao diện danh sách giày.
        return Arrays.asList(
                new Product(
                "4DFWD PULSE SHOES",
                "$160.00",
                "Adidas",
                "This product is excluded from all promotional discounts and offers.",
                "images/img1.png"
        ),
                new Product(
                "FORUM MID SHOES",
                "$100.00",
                "Adidas",
                "Classic basketball-inspired shoes with a retro Adidas look.",
                "images/img2.png"
        ),
                new Product(
                "SUPERNOVA SHOES",
                "$150.00",
                "Adidas",
                "Comfortable running shoes for daily training and walking.",
                "images/img3.png"
        ),
                new Product(
                "NMD CITY STOCK 2",
                "$160.00",
                "Adidas",
                "Modern Adidas shoes with a lightweight design and responsive sole.",
                "images/img4.png"
        ),
                new Product(
                "4DFWD PULSE BLACK",
                "$120.00",
                "Adidas",
                "Sporty black running shoes with a futuristic sole design.",
                "images/img5.png"
        ),
                new Product(
                "4DFWD PULSE ORANGE",
                "$160.00",
                "Adidas",
                "Bright orange running shoes designed for energetic daily use.",
                "images/img6.png"
        ),
                new Product(
                "4DFWD PULSE SHOES",
                "$160.00",
                "Adidas",
                "This product is excluded from all promotional discounts and offers.",
                "images/img1.png"
        ),
                new Product(
                "FORUM MID SHOES",
                "$100.00",
                "Adidas",
                "This product is excluded from all promotional discounts and offers.",
                "images/img2.png"
        ));
    }

    private void addEvents() {
        for (ProductCard card : view.getProductCards()) {
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectCard(card);
                    view.showProductDetail(card.getProduct());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (card != selectedCard) {
                        card.setHoverStyle();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (card != selectedCard) {
                        card.setDefaultStyle();
                    }
                }
            });
        }

        if (!view.getProductCards().isEmpty()) {
            selectCard(view.getProductCards().get(0));
        }
    }

    private void selectCard(ProductCard card) {
        if (selectedCard != null) {
            selectedCard.setDefaultStyle();
        }

        selectedCard = card;
        selectedCard.setSelectedStyle();
    }
}
