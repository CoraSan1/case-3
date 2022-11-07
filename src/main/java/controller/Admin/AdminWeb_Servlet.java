package controller.Admin;

import dao.CRUD_Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(value = "/AdminWeb_Servlet")
public class AdminWeb_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", CRUD_Product.getAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", CRUD_Product.getAll());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin.jsp");
        requestDispatcher.forward(request, response);
    }
}
