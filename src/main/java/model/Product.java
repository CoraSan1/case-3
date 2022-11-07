package model;

public class Product {
    private int id;
    private String name;
    private String img;
    private double price;

    private int amount;

    public Product() {
    }

    public Product(String name, String img, double price, int amount) {
        this.name = name;
        this.img = img;
        this.price = price;
        this.amount = amount;
    }

    public Product(int id, String name, String img, double price, int amount) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
