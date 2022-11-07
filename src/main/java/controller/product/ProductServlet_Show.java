package controller.product;

import dao.CRUD_Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Product;
import service.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = " ProductServlet_Show", value = "/ProductServlet_Show")
public class ProductServlet_Show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", CRUD_Product.getAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = CRUD_Product.getAll();
        request.setAttribute("product", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user.jsp");
        requestDispatcher.forward(request, response);
    }
}
