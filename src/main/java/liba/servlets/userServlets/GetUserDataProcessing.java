package liba.servlets.userServlets;

import liba.dto.UserDTO;
import liba.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 09.01.2016.
 */
public class GetUserDataProcessing extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDTO userDTO = new UserDTO();

        UserServiceImpl userService = new UserServiceImpl();

        String nameValue = req.getParameter("name");

        String loginValue = req.getParameter("login");

        String passwordValue = req.getParameter("password");

        if (nameValue==null | loginValue==null | passwordValue==null){
            req.getRequestDispatcher("/logOut").forward(req,resp);
        }

        userDTO.setName(nameValue);
        userDTO.setLogin(loginValue);
        userDTO.setPassword(passwordValue);

        String birthdayString = req.getParameter("birthday");
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date birthdayValue = null;
        try {
            birthdayValue = format.parse(birthdayString);
        } catch (ParseException e) {
            System.out.println("ERROR OF STRING_TO_DATE TRANSFORMING ");
        }
        userDTO.setBirthday(birthdayValue);

        userService.addUser(userDTO);

        HttpSession session = req.getSession();
        session.setAttribute("currentUser",userDTO);

        req.getRequestDispatcher("/bookCatalog").forward(req, resp);
    }
}



