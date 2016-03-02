package liba.servlets.filters;

import liba.servlets.userServlets.Checking;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16.02.2016.
 */
public class filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        for (Cookie s: cookies){
            if (s.getName().equals("JSESSIONID")){
                String role = Checking.userSession.get(s.getValue());
                if (role==null){
                    req.setAttribute("warning","<b>You have no rights to visit this page.</b>");
                    req.getRequestDispatcher("/logIn.jsp").forward(req,resp);
                }
               else if (role.equals("admin")){
                   filterChain.doFilter(req,resp);

               }
                else {
                   req.setAttribute("warning","<b>You have no rights to visit this page.</b>");
                   req.getRequestDispatcher("/logIn.jsp").forward(req,resp);
               }
            }
        }

    }

    @Override
    public void destroy() {

    }
}
