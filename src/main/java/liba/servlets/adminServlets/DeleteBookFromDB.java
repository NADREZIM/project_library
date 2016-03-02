package liba.servlets.adminServlets;

import liba.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 03.02.2016.
 */
public class DeleteBookFromDB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String[]checkedBooks = req.getParameterValues("answer");
        if (checkedBooks==null){
            req.setAttribute("nullValue","null");
            req.getRequestDispatcher("/bookAmount").forward(req,resp);
        }
        BookServiceImpl bookService = new BookServiceImpl();
        for(String s: checkedBooks){
            long id = Long.parseLong(s);
            bookService.deleteBook(id);
        }
        req.getRequestDispatcher("/bookAmount").forward(req, resp);
    }
}
