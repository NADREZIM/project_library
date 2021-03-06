package liba.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 06.01.2016.
 */
public class dataSource {
    private static dataSource dataSource;
    private ComboPooledDataSource cpds;

    private dataSource()  {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/liba");
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
    public static dataSource getInstance() {
        if (dataSource == null) {
            dataSource = new dataSource();
        }
        return dataSource;
    }
    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}
