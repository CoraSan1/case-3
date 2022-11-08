package dao;


import model.Account;
import model.Bill;
import model.Cart;
import model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CRUD_cart {
    public static void newCart(String userName) {
        try {
            String sql = "INSERT INTO cart (username) VALUES (?);";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Cart getCart(Account account) {
        Cart cart = new Cart();
        String sql = "Select * from cart where username = ?";

        try {
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idcart = resultSet.getInt("idCart");
                String userName = resultSet.getString("username");
                cart = new Cart(idcart, userName);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cart;
    }

    public static List<Cart> getCartAll() {
        List<Cart> carts = new ArrayList<>();
        String sql = "Select * from cart";

        try {
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idcart = resultSet.getInt("idCart");
                String userName = resultSet.getString("userName");
                carts.add(new Cart(idcart, userName));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return carts;
    }

    public static List<Product> getProductCart(String userName) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "select * from cart join detail_cart on detail_cart.idcart = cart.idcart " +
                    "join product on detail_cart.idproduct = product.id" +
                    " where cart.username = ?" +
                    " group by product.id";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idproduct");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String img = resultSet.getString("img");
                int amount = resultSet.getInt("amount");
                products.add(new Product(id, name, img, price, amount));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public static void deleteCart(Account account) {
        try {
            String sql = "DELETE FROM cart WHERE userName = ? ;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int returniddetailcart(int idproduct){
        int iddetailcart = 0;
        String sql = "select iddetail_cart from detail_cart where idproduct = ?";

        try {
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idproduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                iddetailcart = resultSet.getInt("iddetail_cart");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return iddetailcart;
    }

    public static void newdetail(int iddetail_cart ,int idcart, int idproduct, String nameproduct, int amount) {
        try {
            String sql = "insert into detail_cart(iddetail_cart ,idcart,idproduct,nameproduct, amount) value (?,?,?,?,?)";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, iddetail_cart);
            preparedStatement.setInt(2, idcart);
            preparedStatement.setInt(3, idproduct);
            preparedStatement.setString(4, nameproduct);
            preparedStatement.setInt(5, amount);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void newdetail(int idproduct, String nameproduct, int idcart) {
        try {
            String sql = "insert into detail_cart(idproduct,nameproduct, amount, idcart) value (?,?,?,?)";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idproduct);
            preparedStatement.setString(2, nameproduct);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, idcart);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteDeatail(int idproduct) {
        try {
            String sql = "DELETE FROM detail_cart WHERE idproduct = ? ;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idproduct);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteDeatailAdmin(int idCart) {
        try {
            String sql = "DELETE FROM deatail WHERE idcart = ? ;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteBill(Account account) {
        try {
            String sql = "DELETE FROM bill WHERE userName = ? ;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int TotalBill(String userName, int idCart) {
        int total = 0;
        try {
            String sql = " select sum(bill) as totabill  from (select cart.idcart, product.idproduct, (sum( deatail.amout)*product.price) as bill  " + " from cart join deatail on deatail.idcart = cart.idcart " + "                          join product on deatail.idproduct = product.idproduct " + "where userName = ? " + "group by idproduct) as totabill " + "where idcart = ? " + "group by idcart;";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, idCart);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                total += resultSet.getInt("total");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return total;
    }

    public static void newBill(Bill bill) {
        try {
            String sql = "insert into bill value (?,?,?,?,?)";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setInt(1, bill.getIdbill());
            preparedStatement.setString(2, bill.getUsername());
            preparedStatement.setDate(3, Date.valueOf(bill.getDate()));
            preparedStatement.setDouble(4, bill.getTotal());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void UpdateStatus(String userName, int idCart) {
        try {
            String sql = "UPDATE cart set status = 0 where userName = ? and idcart= ? ";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, idCart);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Bill> showBill(Account account) {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "select * from bill where userName = ? ";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idbill = resultSet.getInt("idbill");
                int idcart = resultSet.getInt("idcart");
                String userName = resultSet.getString("username");
                LocalDateTime date = resultSet.getDate("date").toLocalDate().atStartOfDay();
                int total = resultSet.getInt("total");
                String status = resultSet.getString("status");
                bills.add(new Bill(idbill, idcart, userName, date, total, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bills;
    }


    public static List<Bill> showBillAll() {
        List<Bill> bills = new ArrayList<>();
        try {
            String sql = "select * from bill ";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idbill = resultSet.getInt("idbill");
                int idcart = resultSet.getInt("idcart");
                String userName = resultSet.getString("username");
                LocalDateTime date = resultSet.getDate("date").toLocalDate().atStartOfDay();
                int total = resultSet.getInt("total");
                String status = resultSet.getString("status");
                bills.add(new Bill(idbill, idcart, userName, date, total, status));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bills;
    }


    public static int Bill() {
        int Bill = 0;
        try {
            String sql = "select sum(total) as 'Tong' from bill";
            PreparedStatement preparedStatement = CRUD_Account.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bill += resultSet.getInt("Tong");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Bill;
    }

}
