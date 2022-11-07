package dao;

import dao.Connect_MySQL;
import model.Account;

import java.sql.*;
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
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                String address = resultSet.getString("address");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                int id_role = resultSet.getInt("id_role");

                accounts.add(new Account(username1, password1, address, birthday, id_role));
            }

            return accounts;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public static List<Account> getAllUser() {
        String sql = "Select * from account";
        List<Account> accounts = new ArrayList<>();
        try {
            // tạo cái xe để đưa câu lệnh sql qua CSDL
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                int id_role = resultSet.getInt("id_role");

                accounts.add(new Account(username, password, address, birthday, id_role));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    public static void save(Account account) {
        try {
            String sql = "insert into account(username ,password, address, birthday, id_role) value ( ?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getAddress());
            preparedStatement.setString(4, String.valueOf(account.getBirthday()));
            preparedStatement.setInt(5, Integer.parseInt(String.valueOf(account.getId_role())));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void Update(Account account) {
        try {
            String sql = "UPDATE account set name = ?, userpassWord = ?, img = ?  where userName = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getAddress());
            preparedStatement.setString(4, String.valueOf(account.getBirthday()));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void UpdateUser(Account account) {
        try {
            String sql = "UPDATE account set id_role = ?  where username = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getId_role());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteUser(Account account) {
        try {
            String sql = "DELETE FROM account WHERE username = ? ;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
