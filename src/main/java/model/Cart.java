package model;

public class Cart {
    private int idcart;
    private String username;

    public Cart() {
    }

    public Cart(int idcart, String username) {
        this.idcart = idcart;
        this.username = username;
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "cart{" +
                "idcart=" + idcart +
                ", username='" + username + '\'' +
                '}';
    }
}
