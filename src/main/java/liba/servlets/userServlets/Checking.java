package liba.servlets.userServlets;

import liba.dao.implDB.UserDAOImplDB;
import liba.dto.UserDTO;
import liba.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 09.01.2016.
 */
public class Checking extends HttpServlet {
    static public Map<String,String> userSession = new HashMap<String,String>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean direction;

        String loginField = req.getParameter("login");
        String passwordField = req.getParameter("userLoginPassword");
        UserDAOImplDB userDAOImplDB = new UserDAOImplDB();
        HttpSession session = req.getSession(true);
        String sessionID = session.getId();
        direction = userDAOImplDB.userEntranceChecking(loginField, passwordField);
        if (direction) {
            List<String>roles = userDAOImplDB.takeRole(loginField,passwordField);
            boolean b = roles.contains("admin");
                if (b){
                    userSession.put(sessionID,"admin");
                    System.out.println(userSession);
                    String path = req.getRequestURL()+"/admin";
                    UserDTO userDTO = new UserDTO();
                    userDTO.setLogin(loginField);
                    userDTO.setPassword(passwordField);
                    session.setAttribute("currentUser",userDTO);
                    req.setAttribute("adminPath",path);
                    req.getRequestDispatcher("/logIn.jsp").forward(req,resp);

                }
                else {
                    userSession.put(sessionID,"user");
                    System.out.println(userSession);
                    UserDTO userDTO = new UserDTO();
                    userDTO.setLogin(loginField);
                    userDTO.setPassword(passwordField);
                    session.setAttribute("currentUser",userDTO);
                    req.getRequestDispatcher("/bookCatalog").forward(req, resp);
                }
            }
         else {
            req.setAttribute("warning", "<b>Incorrect login or login password. Please try again ones more.</b>");
            req.getRequestDispatcher("/logOut").forward(req, resp);
        }
    }
}
