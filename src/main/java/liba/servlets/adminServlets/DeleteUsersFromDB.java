package liba.servlets.adminServlets;

import liba.service.impl.BookServiceImpl;
import liba.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 09.02.2016.
 */
public class DeleteUsersFromDB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[]checkedBooks = req.getParameterValues("chosenUsers");
        if (checkedBooks==null){
            req.setAttribute("nullValue","null");
            req.getRequestDispatcher("/userAmount").forward(req,resp);
        }
        UserServiceImpl userService = new UserServiceImpl();
        for(String s: checkedBooks){
            long id = Long.parseLong(s);
            userService.deleteUser(id);
        }
        req.getRequestDispatcher("/userAmount").forward(req, resp);
    }
}
