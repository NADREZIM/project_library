package liba.servlets.userServlets;

import liba.dao.implDB.UserDAOImplDB;
import liba.dto.BookDTO;
import liba.dto.ReportDTO;
import liba.dto.UserDTO;
import liba.model.Book;
import liba.service.impl.BookServiceImpl;
import liba.service.impl.ReportServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 18.01.2016.
 */
public class BookCatalogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookDTO>bookCatalog;
        BookServiceImpl bookService = new BookServiceImpl();
        bookCatalog = bookService.getAllBooks();

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        String login = userDTO.getLogin();
        String password = userDTO.getPassword();
        UserDAOImplDB userDAOImplDB = new UserDAOImplDB();
        long userID = userDAOImplDB.findUserID(login,password);

        Map<Date, Book> dateRent_plus_books = userDAOImplDB.findUserRentBooks(userID);
        session.setAttribute("dateRentOfBookAndBooks",dateRent_plus_books);
        session.setAttribute("bookCatalog",bookCatalog);
        req.getRequestDispatcher("userPages/showBookStorage.jsp").forward(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
