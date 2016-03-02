package liba.servlets.adminServlets;

import liba.dto.BookDTO;
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
 * Created by User on 02.02.2016.
 */
public class FindUserWithBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO;
        String userName = req.getParameter("name");
        if (userName==null){
         req.getRequestDispatcher("/logOut").forward(req,resp);
        }
        UserServiceImpl userService = new UserServiceImpl();
        userDTO = userService.findUserByNameWithBook(userName);
        List<BookDTO>listBook = userDTO.getBooks();
        HttpSession session = req.getSession();
        session.setAttribute("listBook",listBook);
        session.setAttribute("userDto",userDTO);
        req.getRequestDispatcher("adminPages/showUserWithBook.jsp").forward(req,resp);

    }
}
