package model;

public class Product {
    private String name;
    private String price;
    private String brand;
    private String description;
    private String imagePath;

    public Product(String name, String price, String brand, String description, String imagePath) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}