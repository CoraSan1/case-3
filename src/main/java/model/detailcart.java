package model;

public class detailcart {
    private int iddetail_cart;
    private int idproduct;
    private String nameproduct;
    private int amount;


    public detailcart() {
    }

    public detailcart(int iddetail_cart, int idproduct, String nameproduct, int amount) {
        this.iddetail_cart = iddetail_cart;
        this.idproduct = idproduct;
        this.nameproduct = nameproduct;
        this.amount = amount;

    }

    public int getIddetail_cart() {
        return iddetail_cart;
    }

    public void setIddetail_cart(int iddetail_cart) {
        this.iddetail_cart = iddetail_cart;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getNameproduct() {
        return nameproduct;
    }

    public void setNameproduct(String nameproduct) {
        this.nameproduct = nameproduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "detailcart{" +
                "iddetail_cart=" + iddetail_cart +
                ", idproduct=" + idproduct +
                ", nameproduct='" + nameproduct + '\'' +
                ", amount=" + amount + '\'' +
                '}';
    }
}
