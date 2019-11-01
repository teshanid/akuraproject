package com.akura.controller;


import com.akura.dao.ClasDao;
import com.akura.dao.SemesterDao;
import com.akura.dao.SemesterexamDao;
import com.akura.entity.Semesterexam;
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
public class SemesterexamController {

    @Autowired
    private SemesterexamDao dao;

    @Autowired
    private ClasDao daoClas;

    @Autowired
    private SemesterDao daoSemester;




    @RequestMapping(value = "/semesterexams", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Semesterexam> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.SEMESTEREXAM,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/semesterexams/list", method = RequestMethod.GET, produces = "application/json")
    public List<Semesterexam> list(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
        if(AuthProvider.isUser(username,password)) {
            return dao.list();
        }
        return null;
    }

    @RequestMapping(value = "/semesterexams", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Semesterexam semesterexam) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTEST, AuthProvider.INSERT)) {

            List<Semesterexam> semesterexams = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Semesterexam> semesterexamstream = semesterexams.stream();


            semesterexamstream = semesterexamstream.filter(e -> e.getClasId().equals(semesterexam.getClasId()));

            semesterexamstream = semesterexamstream.filter(e -> e.getSemesterId().equals(semesterexam.getSemesterId()));


            List<Semesterexam> semesterexams2 = semesterexamstream.collect(Collectors.toList());


            if (!semesterexams2.isEmpty())
                return "Error-Validation : Semester Exam Exists";
            else
                try {
                    dao.save(semesterexam);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/semesterexams", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Semesterexam semesterexam) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.PLACEMENTTEST, AuthProvider.UPDATE)) {
            Semesterexam sem = dao.getOne(semesterexam.getId());
            if (sem == null || sem.getId() == sem.getId()) {
                try {
                    dao.save(semesterexam);
                    return "0";
                } catch (Exception e) {
                    return "Error-Updating : " + e.getMessage();
                }
            } else {
                return "Error-Updating : Exam Exists";
            }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/semesterexams", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Semesterexam semesterexam) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.SEMESTEREXAM, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(semesterexam.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/semesterexams", params = {"page", "size", "clasid", "semesterid", "date"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Semesterexam> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("clasid") Integer clasid, @RequestParam("semesterid") Integer semesterid, @RequestParam("date") String date) {

        System.out.println(clasid+"-"+semesterid+"-"+date);

        if(AuthProvider.isAuthorized(username,password, ModuleList.SEMESTEREXAM,AuthProvider.SELECT)) {

            List<Semesterexam> semesterexams = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Semesterexam> semesterexamStream = semesterexams.stream();

            if (clasid != null)
                semesterexamStream = semesterexamStream.filter(e -> e.getClasId().equals(daoClas.getOne(clasid)));
            if (semesterid != null)
                semesterexamStream = semesterexamStream.filter(e -> e.getSemesterId().equals(daoSemester.getOne(semesterid)));
            if(date!="") {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate examdate = LocalDate.parse(date, formatter);
                semesterexamStream = semesterexamStream.filter(e -> e.getDoexam().equals(examdate));
            }

            List<Semesterexam> semesterexam2 = semesterexamStream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < semesterexam2.size() ? start + size : semesterexam2.size();
            Page<Semesterexam> placpage = new PageImpl<>(semesterexam2.subList(start, end), PageRequest.of(page, size), semesterexam2.size());

            return placpage;
        }



        return null;


    }

   @GetMapping("/semesterexams/listByClas")
    public List<Semesterexam> listByClas(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("clasid") int clasid) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.SEMESTEREXAM,AuthProvider.SELECT)) {
            return dao.listByClas(clasid);
        }
        return null;
    }

  /*  @GetMapping("/examsByClas")
    public List<Semesterexam> examsByClas() {
        return dao.findAll().stream()
                .filter(semesterexam -> semesterexam.getClasId().equals(semesterexam.getClasId()))
                .filter(semesterexam -> semesterexam.getExamstatusId().getId()!=3)
                .collect(Collectors.toList());
    }*/
}
