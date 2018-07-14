package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.Report;
import hr.fer.opp.pizzeriasystem.models.Token;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.models.enums.ReportTimeSpan;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private SecurityService securityService;

    @PostMapping("/admin/reports")
    public ResponseEntity<List<Report>> getReports(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user==null || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }

        return new ResponseEntity<>(reportService.findAll(),HttpStatus.OK);
    }


    // KREIRANJE PDF FORMATA I XSL FORMATA REPORTA
    // TODO:


    //------------------CRON JOBS----------------------------

    //    @Scheduled(cron = "0 0 1 * * MON")
    @Scheduled(cron = "0 0 1 * * MON")
    public void getWeekReport() {
        Date beginDate = getLastWeekDate();
        Date endDate = new Date();

        saveReport(beginDate, endDate, ReportTimeSpan.WEEK);
    }

    @Scheduled(cron = "0 0 1 1 * *")
    public void getMonthReport() {
        Date beginDate = getLastMonthDate();
        Date endDate = new Date();

        saveReport(beginDate, endDate, ReportTimeSpan.MONTH);
    }

    @Scheduled(cron = "0 0 1 1 1/3 *")
    public void getQuarterYearReport() {
        Date beginDate = getLastQuarterYearDate();
        Date endDate = new Date();

        saveReport(beginDate, endDate, ReportTimeSpan.QUARTER_YEAR);
    }

    @Scheduled(cron = "0 0 1 1 1/6 *")
    public void getHalfYearReport() {
        Date beginDate = getLastHalfYearDate();
        Date endDate = new Date();

        saveReport(beginDate, endDate, ReportTimeSpan.HALF_YEAR);
    }

    @Scheduled(cron = "0 0 1 1 1/12 *")
    public void getYearReport() {
        Date beginDate = getLastYearDate();
        Date endDate = new Date();


        saveReport(beginDate, endDate, ReportTimeSpan.YEAR);
    }

    public void saveReport(Date beginDate, Date endDate, ReportTimeSpan reportTimeSpan) {
        Report report = new Report();

        report.setReportBeginDate(beginDate);
        report.setReportEndDate(endDate);
        report.setReportTimeSpan(reportTimeSpan);

        report.setMoneyEarned(reportService.getMoneyEarned(beginDate, endDate));
        report.setDistinctUsersOrdered(reportService.getDistinctUsersOrdered(beginDate, endDate));
        report.setNumberOfOrders(reportService.getNumberOfOrders(beginDate, endDate));
        report.setNumberOfPizzasSold(reportService.getNumberOfPizzasSold(beginDate, endDate));
        report.setMaxOrder(reportService.getMaxOrder(beginDate, endDate));
        report.setMinOrder(reportService.getMinOrder(beginDate, endDate));

        reportService.save(report);
    }

    public Date getLastWeekDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        return calendar.getTime();
    }

    public Date getLastMonthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public Date getLastQuarterYearDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTime();
    }

    public Date getLastHalfYearDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTime();
    }

    public Date getLastYearDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return calendar.getTime();
    }
}
