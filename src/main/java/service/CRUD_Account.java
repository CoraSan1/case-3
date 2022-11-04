package service;

import dao.Connect_MySQL;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CRUD_Account {
    public static Connection connection = Connect_MySQL.getConnect();

    public static List<Account> login() {
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "select * from account ";
            Connection connection = Connect_MySQL.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                String address = resultSet.getString("address");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                int id_role = resultSet.getInt("id_role");

                accounts.add(new Account(id, username1, password1, address, birthday, id_role));
            }

            return accounts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}
