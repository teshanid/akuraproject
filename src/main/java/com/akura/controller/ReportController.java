package com.akura.controller;



import com.akura.util.ModuleList;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ReportController {



    @RequestMapping(value = "/reports/systemaccessanalysis", method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntitySystemAccessAnalysis> systemaccessanalysis(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {
    LocalDateTime startdate = LocalDateTime.now().minusDays(35);
    LocalDateTime enddate = LocalDateTime.now().plusDays(1);
    return ReportProvider.getSystemAccessAnalysisReport(startdate, enddate);
}
else return  null;


    }

    @RequestMapping(value = "/reports/systemaccessanalysis", params = {"startdate", "enddate"}, method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntitySystemAccessAnalysis> systemaccessanalysis2(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime stdate=LocalDateTime.parse(startdate+" 00:00",formatter );
            LocalDateTime endate=LocalDateTime.parse(enddate +" 00:00",formatter);
            return ReportProvider.getSystemAccessAnalysisReport(stdate,endate);
        }
        else return  null;
    }


    @RequestMapping(value = "/reports/studentlist", method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityStudentList> studentlist(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {

            return ReportProvider.getStudentListReport();
        }
        else return  null;


    }

    @RequestMapping(value = "/reports/studentlist", params = {"clasid"},  method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityStudentList> studentlist2(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("clasid") Integer clasid) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.STUDENTCLAS,AuthProvider.SELECT)) {


            return ReportProvider.getStudentListReport2(clasid);
        }
        else return  null;
    }


    @RequestMapping(value = "/reports/studentresults", method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityStudentResults> studentresults(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {

            return ReportProvider.getStudentResultsReport();
        }
        else return  null;


    }

    @RequestMapping(value = "/reports/studentresults", params = {"clasid","semesterexamid"},method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityStudentResults> studentresults2(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("clasid") Integer clasid,@RequestParam("semesterexamid") Integer semesterexamid) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {

            return ReportProvider.getStudentResultsReport2(clasid,semesterexamid);
        }
        else return  null;


    }

}
