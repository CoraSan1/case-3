package dao;

import model.Account;
import service.CRUD_Account;

import java.sql.*;
import java.time.LocalDate;

public class loginDAO {

    public static Account login(String username, String password) {
        try {
            String sql = "select * from account where username = ? and password = ?";
            Connection connection = Connect_MySQL.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            Account account = null;
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username1 = resultSet.getString("username");
                String password1 = resultSet.getString("password");
                String address = resultSet.getString("address");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                int id_role = resultSet.getInt("id_role");

                account = new Account(id, username1, password1, address, birthday, id_role);
            }

            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public static boolean checkUser(String username) {
        for (Account user : CRUD_Account.login()) {
            if (username.equals(user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public static void sign_up(Account account){
        String sql = "INSERT INTO account (username,password,address,birthday, id_role)" +
                "VALUES (?,?,?,?,?);";
        try {
            Connection connection = Connect_MySQL.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getAddress());
            preparedStatement.setDate(4, Date.valueOf(account.getBirthday()));
            preparedStatement.setInt(5, account.getId_role());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

