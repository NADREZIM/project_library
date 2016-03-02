package liba.service.impl;

import com.ibatis.common.jdbc.ScriptRunner;
import liba.connection.connectToBD;
import liba.service.api.DataBaseService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by User on 30.01.2016.
 */
public class DataBaseServiceImpl implements DataBaseService {
    @Override
    public void dataBaseInit(String path) throws SQLException, IOException {

        Connection connection = connectToBD.getInstance().getConnection();
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection, false, false);
            Reader reader = new BufferedReader(new FileReader(path));
            scriptRunner.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + path
                    + " The error is " + e.getMessage());
        } finally {
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}