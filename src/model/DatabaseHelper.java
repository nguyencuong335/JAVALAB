package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:shoestore.db";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found.");
        }
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "price TEXT, " +
                "brand TEXT, " +
                "description TEXT, " +
                "image_path TEXT" +
                ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);

            String countSQL = "SELECT COUNT(*) FROM products";
            try (ResultSet rs = stmt.executeQuery(countSQL)) {
                if (rs.next() && rs.getInt(1) == 0) {
                    insertDefaultProducts(conn);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDefaultProducts(Connection conn) throws SQLException {
        String insertSQL = "INSERT INTO products (name, price, brand, description, image_path) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            Object[][] defaultProducts = {
                {"4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img1.png"},
                {"FORUM MID SHOES", "$100.00", "Adidas", "Classic basketball-inspired shoes with a retro Adidas look.", "images/img2.png"},
                {"SUPERNOVA SHOES", "$150.00", "Adidas", "Comfortable running shoes for daily training and walking.", "images/img3.png"},
                {"NMD CITY STOCK 2", "$160.00", "Adidas", "Modern Adidas shoes with a lightweight design and responsive sole.", "images/img4.png"},
                {"4DFWD PULSE BLACK", "$120.00", "Adidas", "Sporty black running shoes with a futuristic sole design.", "images/img5.png"},
                {"4DFWD PULSE ORANGE", "$160.00", "Adidas", "Bright orange running shoes designed for energetic daily use.", "images/img6.png"},
                {"4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img1.png"},
                {"FORUM MID SHOES", "$100.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img2.png"}
            };

            for (Object[] prod : defaultProducts) {
                pstmt.setString(1, (String) prod[0]);
                pstmt.setString(2, (String) prod[1]);
                pstmt.setString(3, (String) prod[2]);
                pstmt.setString(4, (String) prod[3]);
                pstmt.setString(5, (String) prod[4]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT name, price, brand, description, image_path FROM products";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                products.add(new Product(
                    rs.getString("name"),
                    rs.getString("price"),
                    rs.getString("brand"),
                    rs.getString("description"),
                    rs.getString("image_path")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}