package liba.servlets.userServlets;

import liba.dao.implDB.BookDAOImplDB;
import liba.dao.implDB.UserDAOImplDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * Created by User on 26.02.2016.
 */
public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] list = req.getParameterValues("answer");
        if (list==null){
            req.setAttribute("nullValue","null");
            req.getRequestDispatcher("/bookCatalog").forward(req,resp);
        }
        BookDAOImplDB bookDAOImplDB = new BookDAOImplDB();
        for (String s : list) {
            long id = Long.parseLong(s.substring(0, 1));
            Timestamp ts = Timestamp.valueOf(s.substring(2));
            bookDAOImplDB.returnBook(id, ts);
        }
        req.getRequestDispatcher("/bookCatalog").forward(req,resp);
    }
}
