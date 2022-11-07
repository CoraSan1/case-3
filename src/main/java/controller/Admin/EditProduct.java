package controller.Admin;

import dao.CRUD_Product;
import dao.CRUD_cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Product;

import java.io.IOException;

@WebServlet(name = "EditProduct", value = "/EditProduct")
public class EditProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String img = request.getParameter("img");
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(id, name, img, price, amount);
        String action = request.getParameter("action");
        String url = "";
        switch (action) {
            case "Edit":
                CRUD_Product.updateProduct(product);
                url = "/AdminWeb_Servlet";
                break;
            case "Delete":
                CRUD_cart.deleteDeatail(id);
                CRUD_Product.deleteProduct(product);
                url = "/AdminWeb_Servlet";
                break;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
