package dao;

import model.Account;

import java.sql.*;

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
                Date birthday = resultSet.getDate("date");
                int id_role = resultSet.getInt("id_role");
                account = new Account(id, username1, password1, address, birthday, id_role);
            }

            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}

