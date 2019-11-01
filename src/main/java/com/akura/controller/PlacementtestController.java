package com.akura.controller;

import com.akura.dao.*;
import com.akura.entity.Placementtest;
import com.akura.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class PlacementtestController {

    @Autowired
    private PlacementtestDao dao;

    @Autowired
    private BranchDao daoBranch;

    @Autowired
    private HallDao daoHall;


    @RequestMapping(value = "/placementtests", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Placementtest> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTEST, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/placementtests/list", method = RequestMethod.GET, produces = "application/json")
    public List<Placementtest> placementtest() {
        return dao.list();
    }



    @RequestMapping(value = "/placementtests", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Placementtest placementtest) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.EMPLOYEE, AuthProvider.INSERT)) {

            List<Placementtest> placementtests = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Placementtest> placementteststream = placementtests.stream();
            //placementteststream = placementteststream.filter(e -> !(e.getCallingname().equals("Admin")));


            placementteststream = placementteststream.filter(e -> e.getHallId().equals(placementtest.getHallId()));

            placementteststream = placementteststream.filter(e -> e.getDoexam().equals(placementtest.getDoexam()));

            placementteststream = placementteststream.filter(e -> e.getStarttime().equals(placementtest.getStarttime()));

            placementteststream = placementteststream.filter(e -> e.getEndtime().equals(placementtest.getEndtime()));

            List<Placementtest> placementtests2 = placementteststream.collect(Collectors.toList());


            if (!placementtests2.isEmpty())
                return "Error-Validation : Placement Test Exists";
            else
                try {
                    dao.save(placementtest);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/placementtests", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Placementtest placementtest) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTEST, AuthProvider.UPDATE)) {
            Placementtest plac = dao.getOne(placementtest.getId());
            if (plac == null || plac.getId() == plac.getId()) {
                try {
                    dao.save(placementtest);
                    return "0";
                } catch (Exception e) {
                    return "Error-Updating : " + e.getMessage();
                }
            } else {
                return "Error-Updating : Name Exists";
            }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/placementtests", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Placementtest placementtest) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTEST, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(placementtest.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/placementtests", params = {"page", "size", "branchid", "hallid", "date"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Placementtest> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("branchid") Integer branchid, @RequestParam("hallid") Integer hallid, @RequestParam("date") String date) {

         System.out.println(branchid+"-"+hallid+"-"+date);

        if(AuthProvider.isAuthorized(username,password, ModuleList.PLACEMENTTEST,AuthProvider.SELECT)) {

                List<Placementtest> placementtests = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
                Stream<Placementtest> placementteststream = placementtests.stream();

            if (branchid != null)
                placementteststream = placementteststream.filter(e -> e.getHallId().getBranchId().getName().equals(daoBranch.getOne(branchid).getName()));
            if (hallid != null)
                placementteststream = placementteststream.filter(e -> e.getHallId().equals(daoHall.getOne(hallid)));
if(date!="") {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate examdate = LocalDate.parse(date, formatter);
    placementteststream = placementteststream.filter(e -> e.getDoexam().equals(examdate));
}

            List<Placementtest> placementtest2 = placementteststream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < placementtest2.size() ? start + size : placementtest2.size();
            Page<Placementtest> placpage = new PageImpl<>(placementtest2.subList(start, end), PageRequest.of(page, size), placementtest2.size());

            return placpage;
        }



        return null;


    }

    @RequestMapping(value = "/placementtests/getAvailable", method = RequestMethod.GET, produces = "application/json")
    public List<Placementtest> getAvailable(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTEST, AuthProvider.SELECT)) {
            return dao.findAll().stream()
                    .filter(Placementtest -> Placementtest.getExamstatusId().getId() == 1 || Placementtest.getExamstatusId().getId() == 2)
                    .filter(Placementtest -> Placementtest.getHallId().getCapacity() > Placementtest.getPlacementtestpaymentList().size())
                    .collect(Collectors.toList());
        }
        return null;
    }

   /* @GetMapping("placementtests/getAvailable")
    public List<Placementtest> getAvailable() {
        return dao.findAll().stream()
                .filter(Placementtest -> Placementtest.getExamstatusId().getId() == 1 || Placementtest.getExamstatusId().getId() == 2)
                .filter(Placementtest -> Placementtest.getHallId().getCapacity() > Placementtest.getPlacementtestpaymentList().size())
                .collect(Collectors.toList());
    }*/
}