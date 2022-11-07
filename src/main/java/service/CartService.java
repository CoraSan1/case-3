package service;

import model.Account;
import model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    public static ArrayList<Integer> idCart(Account account, List<Cart> carts){
        ArrayList<Integer> idCart = new ArrayList<>();
        for (Cart cart : carts
        ) {
            if (cart.getUsername().equals(account.getUsername())) {
                idCart.add(cart.getIdcart());
            }
        }
        return idCart;
    }
}
