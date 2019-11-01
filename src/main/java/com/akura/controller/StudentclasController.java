package com.akura.controller;

import com.akura.dao.ClasDao;
import com.akura.dao.StudentclasDao;
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
public class StudentclasController {

    @Autowired
    private StudentclasDao dao;

    @Autowired
    private ClasDao daoClas;


    @RequestMapping(value = "/studentclases", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Studentclas> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENTCLAS, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/studentclases", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Studentclas studentclas) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENTCLAS, AuthProvider.INSERT)) {

            List<Studentclas> studentclases = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Studentclas> studentclasstream = studentclases.stream();

            studentclasstream = studentclasstream.filter(e -> e.getStudentId().equals(studentclas.getStudentId()));

            studentclasstream = studentclasstream.filter(e -> e.getClasId().equals(studentclas.getClasId()));


            List<Studentclas> studentclases2 = studentclasstream.collect(Collectors.toList());


            if (!studentclases2.isEmpty())
                return "Error-Validation : Already Allocated";
            else
                try {
                    dao.save(studentclas);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/studentclases", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Studentclas studentclas) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENTCLAS, AuthProvider.UPDATE)) {
            List<Studentclas> studentclases = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Studentclas> studentclasstream = studentclases.stream();

            studentclasstream = studentclasstream.filter(e -> e.getStudentId().equals(studentclas.getStudentId()));

            studentclasstream = studentclasstream.filter(e -> e.getClasId().equals(studentclas.getClasId()));


            List<Studentclas> studentclases2 = studentclasstream.collect(Collectors.toList());


            if (!studentclases2.isEmpty())
                return "Error-Validation : Already Allocated";
            else
                try {
                    dao.save(studentclas);
                    return "0";
                } catch (Exception e) {
                    return "Error-Updating: " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/studentclases", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Studentclas studentclas) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENTCLAS, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(studentclas.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/studentclases", params = {"page", "size", "number", "callingname", "clasid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Studentclas> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("number") String number, @RequestParam("callingname") String callingname, @RequestParam("clasid") Integer clasid) {

        System.out.println(number + "-" + callingname + "-" + clasid);

        if (AuthProvider.isAuthorized(username, password, ModuleList.STUDENTCLAS, AuthProvider.SELECT)) {

            List<Studentclas> studentclases = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Studentclas> studentclasstream = studentclases.stream();


            studentclasstream = studentclasstream.filter(e -> e.getStudentId().getNumber().startsWith(number));
            studentclasstream = studentclasstream.filter(e -> e.getStudentId().getCallingname().startsWith(callingname));

            if (clasid != null)
                studentclasstream = studentclasstream.filter(e -> e.getClasId().equals(daoClas.getOne(clasid)));

            List<Studentclas> studentclases2 = studentclasstream.collect(Collectors.toList());


            int start = page * size;
            int end = start + size < studentclases2.size() ? start + size : studentclases2.size();
            Page<Studentclas> placpage = new PageImpl<>(studentclases2.subList(start, end), PageRequest.of(page, size), studentclases2.size());

            return placpage;
        }


        return null;


    }



  /*  @GetMapping("studentclases/getAvailableBySubject")
    public List<Studentclas> getAvailableBySubject() {
        return dao.findAll().stream()
                .filter(studentclas -> studentclas.getStudentId().getId().equals(studentclas.getStudentId()) )
                .filter(studentclas -> studentclas.getClasId().getId().equals(studentclas.getClasId()))
                .collect(Collectors.toList());
    }*/

}