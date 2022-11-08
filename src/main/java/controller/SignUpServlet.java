package controller;

import dao.CRUD_cart;
import dao.loginDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        LocalDate birthday = LocalDate.of(2022, 11, 11);
        try {
            birthday = LocalDate.parse(request.getParameter("birthday"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String password = request.getParameter("password");

        String confirm_password = request.getParameter("confirm-password");

        if (username.equals("") || password.equals("") || address.equals("") || birthday == null || confirm_password.equals("")) {
            request.setAttribute("mess2", "Đăng ký không thành công");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(request,response);
        } else if (!password.equals(confirm_password)) {
            response.sendRedirect("/login.jsp");
        } else {
            boolean a = loginDAO.checkUser(username);
            if (a) {
                Account account = new Account();
                account.setUsername(username);
                account.setPassword(password);
                account.setAddress(address);
                account.setBirthday(birthday);
                account.setId_role(2);
                loginDAO.sign_up(account);
                CRUD_cart.newCart(account.getUsername());

                request.setAttribute("mess1", "Đăng ký thành công");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request,response);
            } else {
                request.setAttribute("mess3", "Username đã tồn tại");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request,response);            }
        }

    }
}

