package liba.servlets.adminServlets;

import liba.dto.ReportDTO;
import liba.dto.UserDTO;
import liba.service.impl.ReportServiceImpl;
import liba.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 29.01.2016.
 */
public class ReportAmountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ReportDTO> reportsAmount;
        ReportServiceImpl reportService = new ReportServiceImpl();
        reportsAmount = reportService.getAllReports();
        HttpSession session = req.getSession();
        session.setAttribute("reportsAmount",reportsAmount);
        req.getRequestDispatcher("adminPages/showReportsAmount.jsp").forward(req,resp);
    }
}
