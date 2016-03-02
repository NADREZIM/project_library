package liba.service.api;

import liba.dto.ReportDTO;
import liba.model.Report;

import java.util.List;

/**
 * Created by User on 06.01.2016.
 */
public interface ReportService {
    public ReportDTO addReport(ReportDTO reportDTOs) throws NullPointerException;

    public void deleteReport(long id) throws NullPointerException;

    public List<ReportDTO> getAllReports();
}
