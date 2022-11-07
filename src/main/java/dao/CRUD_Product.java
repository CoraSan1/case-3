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
                int amount = resultSet.getInt("amount");
                products.add(new Product(id, name, img, price, amount));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public static List<Product> getAllProduct(int id){
        String sql = "Select * from product where id = ?";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int Id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                double price = resultSet.getDouble("price");
                int amount = resultSet.getInt("amount");
                products.add(new Product(name, img, price, amount));
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return products;
    }

    public static  Product getProduct(int id){
        String sql = "Select * from product where id = ?";
        Product product = null;
        try {
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int Id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String img = resultSet.getString("img");
                double price = resultSet.getDouble("price");
                int amount = resultSet.getInt("amount");
                product = new Product(name, img, price, amount);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return product;

    }

    public static void save(String name, String img, double price, int amount){
        try {
            String sql = "insert into product (name, img, price, amount) value (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, img);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, amount);
            preparedStatement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void updateProduct(Product product){
        try {
            String sql = "UPDATE product set name = ?, img = ?, price = ?, amount =? where id = ?";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);

            preparedStatement.setInt(5, product.getId());
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getImg());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getAmount());
            preparedStatement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static void deleteProduct(Product product) {
        try {
            String sql = "DELETE FROM product WHERE id = ? ;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
