package service;

import dao.CRUD_Product;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> products = new ArrayList<>();


    public static Product returnProduct(String nameProduct){
        List<Product> products1 = CRUD_Product.getAll();

        for (Product product : products1){
            if (product.getName().equals(nameProduct)){
                return product;
            }
        }
        return null;
    }

}
