package controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/ProductServlet_Show", "/Cart_Product"})
public class UserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null){
            res.sendRedirect("/login");
        }else if (account.getId_role() == 2){
            chain.doFilter(req,res);
        }else {
            PrintWriter printWriter = res.getWriter();
            printWriter.println("<h1> Bạn không có quyền truy cập");
        }
    }
}
