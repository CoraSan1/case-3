package controller.product;

import dao.CRUD_cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import model.Cart;
import model.Product;
import service.ProductService;

import java.io.IOException;

@WebServlet(name = "Buy_Product", value = "/Buy_Product")
public class Buy_Product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Cart cart = CRUD_cart.getCart(account);
        int idcart = cart.getIdcart();

        String nameproduct = request.getParameter("name");
        Product product = ProductService.returnProduct(nameproduct);
        int idproduct = product.getId();
        CRUD_cart.newdetail(idproduct, nameproduct, idcart);
        request.setAttribute("id", product.getId());

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ProductServlet_Show");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
