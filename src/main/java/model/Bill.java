package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bill {
    private int idbill;
    private int idcart;
    private String username;
    private LocalDate date;
    private double total;
    private String status;

    public Bill() {
    }

    public Bill(int idbill, int idcart, String userName, LocalDateTime date, int total, String status) {
    }

    public Bill(int idbill, int idcart, String username, LocalDate date, double total, String status) {
        this.idbill = idbill;
        this.idcart = idcart;
        this.username = username;
        this.date = date;
        this.total = total;
        this.status = status;
    }

    public int getIdbill() {
        return idbill;
    }

    public void setIdbill(int idbill) {
        this.idbill = idbill;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
