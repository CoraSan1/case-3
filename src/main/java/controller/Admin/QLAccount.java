package controller.Admin;

import dao.CRUD_Account;
import dao.CRUD_cart;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Account;
import service.CartService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QLAccount", value = "/QLAccount")
public class QLAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Account> accounts =CRUD_Account.getAllUser();
        request.setAttribute("acount", accounts);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AdminQLAccount.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
        int roles = Integer.parseInt(request.getParameter("id_role"));
        Account account = new Account(username, password, address, birthday, roles);
        String url = "/QLAccount";
        String action = request.getParameter("action");
        switch (action) {
            case "Edit":
                CRUD_Account.UpdateUser(account);
                url = "/QLAccount";
                break;
            case "Delete":
                CRUD_cart.deleteBill(account);
                ArrayList<Integer> idCart = CartService.idCart(account, CRUD_cart.getCartAll());
                for (Integer integer : idCart) {
                    CRUD_cart.deleteDeatailAdmin(integer);
                }
                CRUD_cart.deleteCart(account);
                CRUD_Account.deleteUser(account);
                url = "/QLAccount";
                break;
        }
        response.sendRedirect(url);
    }
}
