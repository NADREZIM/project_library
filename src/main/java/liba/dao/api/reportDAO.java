package liba.dao.api;

import liba.model.Report;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public interface reportDAO {

    public Report addReport(Report report) throws NullPointerException;

    public void deleteReport(long id) throws NullPointerException;

    public List<Report> getAllReports();
}
