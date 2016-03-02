package liba.servlets.adminServlets;

import liba.dto.BookDTO;
import liba.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 02.03.2016.
 */
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String authorFromForm = req.getParameter("author");
    String titleFromForm = req.getParameter("title");
    String countFromForm = req.getParameter("count");
        if (authorFromForm == null | titleFromForm == null | countFromForm == null ){
            req.getRequestDispatcher("/logOut").forward(req,resp);
        }
        int count = Integer.parseInt(countFromForm);
        BookServiceImpl bookService = new BookServiceImpl();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(authorFromForm);
        bookDTO.setTitle(titleFromForm);
        bookDTO.setCount(count);
        bookService.addBook(bookDTO);
        req.getRequestDispatcher("adminPages/addBookForm.jsp").forward(req,resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
