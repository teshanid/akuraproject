package com.akura.controller;

import com.akura.dao.*;
import com.akura.entity.Placementtestpayment;
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
public class PtestpaymentController {

    @Autowired
    private PtestpaymentDao dao;

    @Autowired
    private GradeDao daoGrade;




    @RequestMapping(value = "/ptestpayments", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Placementtestpayment> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTESTALLOCATIONANDPAYMENT, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/ptestpayments", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Placementtestpayment placementtestpayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTESTALLOCATIONANDPAYMENT, AuthProvider.INSERT)) {



            List<Placementtestpayment> placementtestpayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Placementtestpayment> placementtestpaymentstream = placementtestpayments.stream();

        placementtestpaymentstream = placementtestpaymentstream.filter(e -> e.getStudentId().equals(placementtestpayment.getStudentId()));

        placementtestpaymentstream = placementtestpaymentstream.filter(e -> e.getPlacementtestId().equals(placementtestpayment.getPlacementtestId()));


            List<Placementtestpayment> placementtestpayments2 = placementtestpaymentstream.collect(Collectors.toList());

            if (!placementtestpayments2.isEmpty())
                return "Error-Validation : Already Allocated and Paid";
            else
                try {
                    dao.save(placementtestpayment);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/ptestpayments", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Placementtestpayment placementtestpayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENTCLAS, AuthProvider.UPDATE)) {
            List<Placementtestpayment> placementtestpayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Placementtestpayment> placementtestpaymentstream = placementtestpayments.stream();

            placementtestpaymentstream = placementtestpaymentstream.filter(e -> e.getStudentId().equals(placementtestpayment.getStudentId()));

            placementtestpaymentstream = placementtestpaymentstream.filter(e -> e.getPlacementtestId().equals(placementtestpayment.getPlacementtestId()));


            List<Placementtestpayment> placementtestpayments2 = placementtestpaymentstream.collect(Collectors.toList());


            if (!placementtestpayments2.isEmpty())
                return "Error-Validation : Already Allocated";
            else
                try {
                    dao.save(placementtestpayment);
                    return "0";
                } catch (Exception e) {
                    return "Error-Updating: " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/ptestpayments", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Placementtestpayment placementtestpayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTESTALLOCATIONANDPAYMENT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(placementtestpayment.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/ptestpayments", params = {"page", "size", "number", "callingname", "gradeid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Placementtestpayment> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("number") String number, @RequestParam("callingname") String callingname, @RequestParam("gradeid") Integer gradeid) {

         System.out.println(number+"-"+callingname+"-"+gradeid);

        if(AuthProvider.isAuthorized(username,password, ModuleList.PLACEMENTTESTALLOCATIONANDPAYMENT,AuthProvider.SELECT)) {

                List<Placementtestpayment> placementtests = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
                Stream<Placementtestpayment> placementteststream = placementtests.stream();

            placementteststream = placementteststream.filter(e -> e.getStudentId().getNumber().startsWith(number));
            placementteststream = placementteststream.filter(e -> e.getStudentId().getCallingname().startsWith(callingname));

            if (gradeid != null)
                placementteststream = placementteststream.filter(e -> e.getGradeId().equals(daoGrade.getOne(gradeid)));



            List<Placementtestpayment> placementtest2 = placementteststream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < placementtest2.size() ? start + size : placementtest2.size();
            Page<Placementtestpayment> ptestpage = new PageImpl<>(placementtest2.subList(start, end), PageRequest.of(page, size), placementtest2.size());

            return ptestpage;
        }



        return null;


    }
}