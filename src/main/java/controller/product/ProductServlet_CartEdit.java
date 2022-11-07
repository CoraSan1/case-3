package controller.product;

import dao.CRUD_cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import model.Bill;
import model.Cart;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ProductServlet_CartEdit", value = "/ProductServlet_CartEdit")
public class ProductServlet_CartEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart = CRUD_cart.getCart(account);

        int amount = Integer.parseInt(request.getParameter("amount"));

        String nameProduct = request.getParameter("name");
        Product product = ProductService.returnProduct(nameProduct);
        int idproduct = product.getId();

        String action = request.getParameter("action");
        String url = "";
        switch (action){
            case "Edit":
                int iddetail_cart = CRUD_cart.returniddetailcart(idproduct);
                CRUD_cart.deleteDeatail(idproduct);
                CRUD_cart.newdetail( iddetail_cart,cart.getIdcart(), idproduct, nameProduct, amount);
                url = "/Cart_Product";
                break;
            case "Delete":
                CRUD_cart.deleteDeatail(idproduct);
                url = "/Cart_Product";
                break;
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
