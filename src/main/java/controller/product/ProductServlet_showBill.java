package controller.product;

import dao.CRUD_cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;

import java.io.IOException;

@WebServlet(name = "ProductServlet_showBill", value = "/ProductServlet_showBill")
public class ProductServlet_showBill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("User");
        request.setAttribute("bill", CRUD_cart.showBill(account));

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WedUser/WebUserHistory.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
