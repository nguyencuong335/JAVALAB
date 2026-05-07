import controller.ShoeStoreController;
import view.ShoeStoreView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShoeStoreView view = new ShoeStoreView();
            new ShoeStoreController(view);
            view.setVisible(true);
        });
    }
}