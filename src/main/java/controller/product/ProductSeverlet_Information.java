package controller.product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import service.AccountService;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ProductSeverlet_Information", value = "/ProductSeverlet_Information")
public class ProductSeverlet_Information extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("Username");
        request.setAttribute("Username", account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WedUser/WebUserInformation.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthay"));
        int role = Integer.parseInt(request.getParameter("role"));
        Account account1 =(Account) session.getAttribute("username");
        Account account = new Account(username, password, address, birthday, 2);
        AccountService.editAccount(account);
        session.setAttribute("User", account);
        request.setAttribute("User", account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WedUser/WebUserInformation.jsp");
        requestDispatcher.forward(request, response);
    }
}
