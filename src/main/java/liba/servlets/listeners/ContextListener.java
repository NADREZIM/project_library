package liba.servlets.listeners;


import liba.dto.BookDTO;
import liba.service.impl.BookServiceImpl;
import liba.service.impl.DataBaseServiceImpl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by User on 30.01.2016.
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        String realPath = servletContextEvent.getServletContext().getRealPath("resources/sqlDataBasesCreation/library_init.sql");
            DataBaseServiceImpl dataBaseService = new DataBaseServiceImpl();
            try {
                dataBaseService.dataBaseInit(realPath);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }



    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
