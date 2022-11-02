package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD_Product {
    static Connection connection = Connect_MySQL.getConnect();

    public static List<Product> getAll() {
        String sql = "Select * from product";
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                double price = resultSet.getDouble("price");
                products.add(new Product(id, name, img, price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public static void save(Product product){
        try {
            String sql = "insert into product value (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getImg());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
