package com.akura.controller;


import com.akura.dao.ClasDao;
import com.akura.dao.SemesterexampaymentDao;
import com.akura.dao.SemesterDao;
import com.akura.entity.Semesterexampayment;
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
public class SemesterexampaymentController {

    @Autowired
    private SemesterexampaymentDao dao;

    @Autowired
    private ClasDao daoClas;

    @Autowired
    private SemesterDao daoSemester;




    @RequestMapping(value = "/semesterexampayments", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Semesterexampayment> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.SEMESTEREXAMPAYMENT,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/semesterexampayments", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Semesterexampayment semesterexampayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.SEMESTEREXAMPAYMENT, AuthProvider.INSERT)) {

            List<Semesterexampayment> semesterexampayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Semesterexampayment> semesterexampaymentstream = semesterexampayments.stream();


            semesterexampaymentstream = semesterexampaymentstream.filter(e -> e.getStudentId().equals(semesterexampayment.getStudentId()));

            semesterexampaymentstream = semesterexampaymentstream.filter(e -> e.getSemesterexamId().equals(semesterexampayment.getSemesterexamId()));


            List<Semesterexampayment> semesterexampayments2 = semesterexampaymentstream.collect(Collectors.toList());

            if(semesterexampayments2.isEmpty()) {
                try {
                    dao.save(semesterexampayment);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Saving : "+e.getMessage();
                }
            }
            else {  return "Error-Saving : Semesterexampayment Exists"; }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/semesterexampayments", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Semesterexampayment semesterexampayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.SEMESTEREXAMPAYMENT, AuthProvider.UPDATE)) {

            List<Semesterexampayment> semesterexampayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Semesterexampayment> semesterexampaymentstream = semesterexampayments.stream();


            semesterexampaymentstream = semesterexampaymentstream.filter(e -> e.getStudentId().equals(semesterexampayment.getStudentId()));

            semesterexampaymentstream = semesterexampaymentstream.filter(e -> e.getSemesterexamId().equals(semesterexampayment.getSemesterexamId()));


            List<Semesterexampayment> semesterexampayments2 = semesterexampaymentstream.collect(Collectors.toList());

            if(semesterexampayments2.isEmpty()) {
                try {
                    dao.save(semesterexampayment);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Semesterexampayment Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/semesterexampayments", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Semesterexampayment semesterexampayment) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.SEMESTEREXAMPAYMENT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(semesterexampayment.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/semesterexampayments", params = {"page", "size", "clasid", "semesterid", "number","callingname"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Semesterexampayment> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size,
                                @RequestParam("clasid") Integer clasid, @RequestParam("semesterid") Integer semesterid, @RequestParam("number") String number, @RequestParam("callingname") String callingname) {

        System.out.println(clasid+"-"+semesterid+"-"+number+"-"+callingname);

        if(AuthProvider.isAuthorized(username,password, ModuleList.SEMESTEREXAMPAYMENT,AuthProvider.SELECT)) {

            List<Semesterexampayment> semesterexampayments = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Semesterexampayment> semesterexampaymentStream = semesterexampayments.stream();

            if (clasid != null)
                semesterexampaymentStream = semesterexampaymentStream.filter(e -> e.getSemesterexamId().getClasId().equals(daoClas.getOne(clasid)));
            if (semesterid != null)
                semesterexampaymentStream = semesterexampaymentStream.filter(e -> e.getSemesterexamId().getSemesterId().equals(daoSemester.getOne(semesterid)));
            semesterexampaymentStream = semesterexampaymentStream.filter(e -> e.getStudentId().getNumber().startsWith(number));
            semesterexampaymentStream = semesterexampaymentStream.filter(e -> e.getStudentId().getCallingname().startsWith(callingname));


            List<Semesterexampayment> semesterexampayment2 = semesterexampaymentStream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < semesterexampayment2.size() ? start + size : semesterexampayment2.size();
            Page<Semesterexampayment> respage = new PageImpl<>(semesterexampayment2.subList(start, end), PageRequest.of(page, size), semesterexampayment2.size());

            return respage;
        }

            return null;
        }



    }




