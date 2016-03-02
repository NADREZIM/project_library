package liba.servlets.adminServlets;

import liba.dto.BookDTO;
import liba.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 10.02.2016.
 */
public class BookAmountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDTO> booksAmount;
        BookServiceImpl bookService = new BookServiceImpl();
        booksAmount = bookService.getAllBooks();
        HttpSession session = req.getSession();
        session.setAttribute("booksAmount",booksAmount);
        req.getRequestDispatcher("adminPages/showBooksAmount.jsp").forward(req,resp);
    }
}
