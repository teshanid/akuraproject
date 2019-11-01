package com.akura.controller;

import com.akura.dao.ClasDao;
import com.akura.dao.ClaspaymentDao;
import com.akura.dao.MonthDao;
import com.akura.dao.StudentclasDao;
import com.akura.entity.Claspayment;
import com.akura.entity.Studentclas;
import com.akura.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ClaspaymentController {
    @Autowired
    private ClaspaymentDao dao;

    @Autowired
    private ClasDao daoClas;

    @Autowired
    private StudentclasDao daoStudentClas;

    @Autowired
    private MonthDao daoMonth;



    @RequestMapping(value = "/claspayments", method = RequestMethod.GET,produces = "application/json")
    public Page<Claspayment> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.CLASPAYMENT, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/claspayments", method = RequestMethod.POST,  produces = "application/json")
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Claspayment claspayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.CLASPAYMENT, AuthProvider.INSERT)) {

            List<Claspayment> claspayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Claspayment> claspaymentstream = claspayments.stream();

            claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getStudentId().getId().equals(claspayment.getStudentclasId().getStudentId().getId()));

            claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getClasId().equals(claspayment.getStudentclasId().getClasId()));

            claspaymentstream = claspaymentstream.filter(e -> e.getMonthId().equals(claspayment.getMonthId()));

            Integer studentid =  claspayment.getStudentclasId().getStudentId().getId();
            Integer classid = claspayment.getStudentclasId().getClasId().getId();
            Studentclas sc = daoStudentClas.findByStudentandClas(studentid,classid);

            claspayment.setStudentclasId(sc);

            List<Claspayment> claspayments2 = claspaymentstream.collect(Collectors.toList());


            if (!claspayments2.isEmpty())
                return "Error-Validation : Already Paid";
            else
                try {
                    dao.save(claspayment);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/claspayments", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Claspayment claspayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.CLASPAYMENT, AuthProvider.UPDATE)) {
            List<Claspayment> claspayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Claspayment> claspaymentstream = claspayments.stream();


            claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getStudentId().equals(claspayment.getStudentclasId().getStudentId()));

            claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getClasId().equals(claspayment.getStudentclasId().getClasId()));

            claspaymentstream = claspaymentstream.filter(e -> e.getMonthId().equals(claspayment.getMonthId()));


            List<Claspayment> claspayments2 = claspaymentstream.collect(Collectors.toList());


            if (!claspayments2.isEmpty())
                return "Error-Validation : Already Paid";
            else
                try {
                    dao.save(claspayment);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/claspayments", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Claspayment claspayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.CLASPAYMENT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(claspayment.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/claspayments", params = {"page", "size", "number", "callingname", "clasid", "monthid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Claspayment> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("number") String number, @RequestParam("callingname") String callingname, @RequestParam("clasid") Integer clasid, @RequestParam("monthid") Integer monthid) {

        System.out.println(number + "-" + callingname + "-" + clasid + "-" + monthid);

        if (AuthProvider.isAuthorized(username, password, ModuleList.CLASPAYMENT, AuthProvider.SELECT)) {

            List<Claspayment> claspayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Claspayment> claspaymentstream = claspayments.stream();


            claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getStudentId().getNumber().startsWith(number));
            claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getStudentId().getCallingname().startsWith(callingname));

            if (clasid != null)
                claspaymentstream = claspaymentstream.filter(e -> e.getStudentclasId().getClasId().equals(daoClas.getOne(clasid)));

            if (monthid != null)
                claspaymentstream = claspaymentstream.filter(e -> e.getMonthId().equals(daoMonth.getOne(monthid)));

            List<Claspayment> claspayments2 = claspaymentstream.collect(Collectors.toList());


            int start = page * size;
            int end = start + size < claspayments2.size() ? start + size : claspayments2.size();
            Page<Claspayment> clpaypage = new PageImpl<>(claspayments2.subList(start, end), PageRequest.of(page, size), claspayments2.size());

            return clpaypage;
        }



        return null;


    }
}