package com.akura.controller;



import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReportProvider {

    public static List<ReportEntitySystemAccessAnalysis> getSystemAccessAnalysisReport(LocalDateTime startdate, LocalDateTime enddate) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT d.name as name, count(*) as attempt FROM schoolofenglish.sessionlog as s, schoolofenglish.user as u, schoolofenglish.employee as e, schoolofenglish.designation as d where s.user_id=u.id and u.employee_id=e.id and e.designation_id=d.id and s.logintime between '" + startdate + "' and '" + enddate + "' group by d.id ;";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntitySystemAccessAnalysis> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntitySystemAccessAnalysis report = new ReportEntitySystemAccessAnalysis(rs.getString("name"), rs.getInt("attempt"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }



    public static List<ReportEntityStudentList> getStudentListReport() {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT number as number, fullname as fullname FROM student;";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntityStudentList> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntityStudentList report = new ReportEntityStudentList(rs.getString("number"), rs.getString("fullname"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }


    public static List<ReportEntityStudentList> getStudentListReport2(Integer clasid) {


            try {
                AuthProvider.setConnection();
                Statement st = AuthProvider.connection.createStatement();
                String query = "SELECT number as number, fullname as fullname FROM student,studentclas,clas where studentclas.student_id = student.id AND studentclas.clas_id = clas.id and clas.id ='"+clasid+"' ;";
                ResultSet rs = st.executeQuery(query);

                List<ReportEntityStudentList> list = new ArrayList<>();

                while (rs.next()) {
                    ReportEntityStudentList report = new ReportEntityStudentList(rs.getString("number"), rs.getString("fullname"));
                    list.add(report);
                }
                return list;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }

    }


    public static List<ReportEntityStudentResults> getStudentResultsReport() {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT number as number, fullname as fullname, result as marks FROM student ,result,semesterexam where result.student_id = student.id AND result.semesterexam_id = semesterexam.id;";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntityStudentResults> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntityStudentResults report = new ReportEntityStudentResults(rs.getString("number"), rs.getString("fullname"),rs.getBigDecimal("marks"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    public static List<ReportEntityStudentResults> getStudentResultsReport2(Integer clasid,Integer semesterexamid) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT number as number, fullname as fullname, result as marks FROM student ,result,semesterexam where result.student_id = student.id AND result.semesterexam_id = semesterexam.id AND clasid='"+clasid+"'AND semesterexamid='"+semesterexamid+"'";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntityStudentResults> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntityStudentResults report = new ReportEntityStudentResults(rs.getString("number"), rs.getString("fullname"),rs.getBigDecimal("marks"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }

    }

/*    public static List<ReportEntityPlacementTestPayment> getReportPlacementTestPayment(LocalDate dopaid) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT s.name as student, g.name as grade,p.name as placementtest FROM akura.student as s, akura.grade as g, akura.placenettest as p, akura.placementtestpayment as t where student_id=studentid and grade_id=gradeid and placementtest_id=placementtestid and dopaid='" + dopaid+"' group by p.id ;";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntitySystemAccessAnalysis> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntitySystemAccessAnalysis report = new ReportEntitySystemAccessAnalysis(rs.getString("name"), rs.getInt("attempt"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }*/
}



