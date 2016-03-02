package liba.servlets.adminServlets;

import liba.dto.UserDTO;
import liba.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 28.01.2016.
 */
public class UserAmountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO>usersAmount;
        String filterChecking = "check";
        UserServiceImpl userService = new UserServiceImpl();
        usersAmount = userService.getAllUsers();
        HttpSession session = req.getSession();
        session.setAttribute("usersAmount",usersAmount);
        req.setAttribute("filterChecking",filterChecking);
        req.getRequestDispatcher("adminPages/showUsersAmount.jsp").forward(req,resp);
    }
}
