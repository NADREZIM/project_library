package liba.service.api;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by User on 30.01.2016.
 */
public interface DataBaseService {
    void dataBaseInit(String path) throws SQLException, IOException;
}
