package liba.dao.implDB;

import liba.connection.dataSource;
import liba.dao.api.reportDAO;
import liba.model.Book;
import liba.model.Report;
import liba.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class ReportDAOImplDB implements reportDAO {
    private static final String GET_ALL_REPORTS = "select*from report";

    @Override
    public Report addReport(Report report) throws NullPointerException {
        return null;
    }

    @Override
    public void deleteReport(long id) throws NullPointerException {

    }

    @Override
    public List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<Report>();
        Statement statement = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = dataSource.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ALL_REPORTS);
            while (rs.next()) {
                Report report = new Report();
                User user = new User();
                Book book = new Book();
                report.setId(rs.getInt(1));
                report.setReturnBook(rs.getDate(2));
                java.util.Date date = rs.getTimestamp(3);
                report.setRentBook(date);
                book.setId(rs.getInt(4));
                book.setAuthor(rs.getString(5));
                book.setTitle(rs.getString(6));
                book.setCount(rs.getInt(7));
                user.setId(rs.getInt(8));
                user.setName(rs.getString(9));
                user.setLogin(rs.getString(10));
                user.setPassword(rs.getString(11));
                user.setBirthday(rs.getDate(12));
                report.setUser(user);
                report.setBook(book);
                reportList.add(report);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return reportList;

    }
}
