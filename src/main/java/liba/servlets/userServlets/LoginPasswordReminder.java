package liba.servlets.userServlets;

import liba.dao.implDB.UserDAOImplDB;
import liba.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by User on 09.02.2016.
 */
public class LoginPasswordReminder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOImplDB userDAOImplDB = new UserDAOImplDB();
        String loginAnswerFromDB;
        String passwordAnswerFromDB;
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null | password == null) {
            req.getRequestDispatcher("/logOut").forward(req, resp);
        }

        if (login.equals("") & password.equals("")) {
            req.getRequestDispatcher("/logOut").forward(req, resp);
        } else if (login.equals("")) {
            loginAnswerFromDB = userDAOImplDB.LoginReminder(password);
            if (loginAnswerFromDB.equals("!!!")) {
                req.getRequestDispatcher("/logOut").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("forgotten data", loginAnswerFromDB);
                req.getRequestDispatcher("userPages/pageReminder.jsp").forward(req, resp);
            }
        } else if (password.equals("")) {
            passwordAnswerFromDB = userDAOImplDB.PasswordReminder(login);
            if (passwordAnswerFromDB.equals("!!!")) {

                req.getRequestDispatcher("/logOut").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("forgotten data", passwordAnswerFromDB);
                req.getRequestDispatcher("userPages/pageReminder.jsp").forward(req, resp);
            }
        }

    }
}
