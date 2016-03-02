package liba.servlets.userServlets;


import liba.dao.implDB.BookDAOImplDB;
import liba.dao.implDB.UserDAOImplDB;
import liba.dto.UserDTO;
import liba.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by User on 10.01.2016.
 */
public class ConnectUserWithBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("currentUser");
        if (userDTO == null) {
            req.getRequestDispatcher("/logOut").forward(req, resp);
        }

        String userLogin = userDTO.getLogin();
        String userPassword = userDTO.getPassword();

        UserDAOImplDB userDAOImplDB = new UserDAOImplDB();

        long id_number = userDAOImplDB.findUserID(userLogin, userPassword);

        String id_Value = req.getParameter("answer");
        if (id_Value == null) {
            req.getRequestDispatcher("/logOut").forward(req, resp);
        }
        Long idLongValue = Long.parseLong(id_Value);

        BookDAOImplDB bookDAOImplDB = new BookDAOImplDB();
        int count = bookDAOImplDB.bookCountChecking(idLongValue);
        if (count==0){
            req.setAttribute("BookCountEnding","there is no such a books in the storage");
            req.getRequestDispatcher("userPages/showBookStorage.jsp").forward(req,resp);
        }

        UserServiceImpl userService = new UserServiceImpl();
        userService.connectUserWithBook(id_number, idLongValue);

        req.getRequestDispatcher("userPages/thanks.html").forward(req, resp);


    }
}
