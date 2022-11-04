package model;

import java.sql.Date;
import java.time.LocalDate;

public class Account {
    private int id;
    private String username;
    private String password;
    private String address;
    private LocalDate birthday;

    private int id_role;

    public Account() {
    }

    public Account(String username, String password, String address, LocalDate birthday, int id_role) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.id_role = id_role;
    }

    public Account(int id, String username, String password, String address, LocalDate birthday, int id_role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.id_role = id_role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}

