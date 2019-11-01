package com.akura.controller;


import com.akura.dao.ClasDao;
import com.akura.dao.SemesterDao;
import com.akura.dao.ResultDao;
import com.akura.entity.Result;
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
public class ResultController {

    @Autowired
    private ResultDao dao;

    @Autowired
    private ClasDao daoClas;

    @Autowired
    private SemesterDao daoSemester;




    @RequestMapping(value = "/results", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Result> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.RESULT,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Result result) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.RESULT, AuthProvider.INSERT)) {

            List<Result> results = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Result> resultstream = results.stream();


            resultstream = resultstream.filter(e -> e.getStudentId().equals(result.getStudentId()));
            resultstream = resultstream.filter(e -> e.getSemesterexamId().equals(result.getSemesterexamId()));


            List<Result> results2 = resultstream.collect(Collectors.toList());

            if(results2.isEmpty()) {
                try {
                    dao.save(result);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Saving : "+e.getMessage();
                }
            }
            else {  return "Error-Saving : Result Exists"; }
        }
        return "Error-Saving : You have no Permission";
    }

    @RequestMapping(value = "/results", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Result result) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.RESULT, AuthProvider.UPDATE)) {

            List<Result> results = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Result> resultstream = results.stream();


            resultstream = resultstream.filter(e -> e.getStudentId().equals(result.getStudentId()));
            resultstream = resultstream.filter(e -> e.getSemesterexamId().getClasId().equals(result.getSemesterexamId().getClasId()));
            resultstream = resultstream.filter(e -> e.getSemesterexamId().equals(result.getSemesterexamId()));


            List<Result> results2 = resultstream.collect(Collectors.toList());

            if(!results2.isEmpty()) {
                try {
                    dao.save(result);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Result Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/results", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Result result) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.RESULT, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(result.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/results", params = {"page", "size", "clasid", "semesterid", "number","callingname"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Result> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size,
                                @RequestParam("clasid") Integer clasid, @RequestParam("semesterid") Integer semesterid, @RequestParam("number") String number, @RequestParam("callingname") String callingname) {

        System.out.println(clasid+"-"+semesterid+"-"+number+"-"+callingname);

        if(AuthProvider.isAuthorized(username,password, ModuleList.RESULT,AuthProvider.SELECT)) {

            List<Result> results = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Result> resultStream = results.stream();

            if (clasid != null)
                resultStream = resultStream.filter(e -> e.getSemesterexamId().getClasId().equals(daoClas.getOne(clasid)));
            if (semesterid != null)
                resultStream = resultStream.filter(e -> e.getSemesterexamId().getSemesterId().equals(daoSemester.getOne(semesterid)));
            resultStream = resultStream.filter(e -> e.getStudentId().getNumber().startsWith(number));
            resultStream = resultStream.filter(e -> e.getStudentId().getCallingname().startsWith(callingname));


            List<Result> result2 = resultStream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < result2.size() ? start + size : result2.size();
            Page<Result> respage = new PageImpl<>(result2.subList(start, end), PageRequest.of(page, size), result2.size());

            return respage;
        }

            return null;
        }



    }




