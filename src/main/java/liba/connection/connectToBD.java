package liba.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 04.02.2016.
 */
public class connectToBD {
    private static connectToBD connectToBD;
    private ComboPooledDataSource cpds;

    private connectToBD()  {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306");
            cpds.setUser("root");
            cpds.setPassword("root");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public static connectToBD getInstance() {
        if (connectToBD == null) {
            connectToBD = new connectToBD();
        }
        return connectToBD;
    }
    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
