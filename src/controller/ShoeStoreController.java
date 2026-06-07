package controller;

import model.DatabaseHelper;
import model.Product;
import view.ProductCard;
import view.ShoeStoreView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        DatabaseHelper.initializeDatabase();
        return DatabaseHelper.getAllProducts();
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
