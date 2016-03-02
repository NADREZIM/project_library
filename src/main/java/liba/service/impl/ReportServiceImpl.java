package liba.service.impl;

import liba.Transformer.Transformer;
import liba.dao.api.reportDAO;
import liba.dao.implDB.ReportDAOImplDB;
import liba.dto.ReportDTO;
import liba.model.Report;
import liba.service.api.ReportService;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public class ReportServiceImpl implements ReportService {


    @Override
    public ReportDTO addReport(ReportDTO reportDTOs) throws NullPointerException {
        reportDAO reportDAOCurrent = new ReportDAOImplDB();
        Report report = Transformer.TRANSFORM_REPORTDTO_TO_REPORT(reportDTOs);
        Report tempReport = reportDAOCurrent.addReport(report);
        return Transformer.TRANSFORM_REPORT_TO_REPORTDTO(tempReport);
    }

    @Override
    public void deleteReport(long id) throws NullPointerException {
        reportDAO reportDAOCurrent = new ReportDAOImplDB();
        reportDAOCurrent.deleteReport(id);
    }

    @Override
    public List<ReportDTO> getAllReports() {
        reportDAO reportDAOCurrent = new ReportDAOImplDB();
        List<Report>reports = reportDAOCurrent.getAllReports();
        return Transformer.TRANSFORM_REPORTLIST_TO_REPORTDTOLIST(reports);
    }
}
