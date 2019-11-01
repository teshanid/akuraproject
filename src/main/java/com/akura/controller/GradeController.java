package com.akura.controller;

import com.akura.dao.GradeDao;
import com.akura.entity.Grade;
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
public class GradeController {

    @Autowired
    private GradeDao dao;


    @RequestMapping(value = "/grades/list", method = RequestMethod.GET, produces = "application/json")
    public List<Grade> grade() { return dao.list();
    }

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Grade grade) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.GRADE,AuthProvider.INSERT)) {
            Grade grname = dao.findByName(grade.getName());
            if (grname != null)
                return "Error-Validation : Grade Exists";
            else
                try {
                    dao.save(grade);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/grades", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Grade grade) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.GRADE,AuthProvider.INSERT)) {
            Grade grname = dao.findByName(grade.getName());
            if (grname != null)
                return "Error-Validation : Grade Exists";
            else
                try {
                    dao.save(grade);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


    @RequestMapping(value = "/grades", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Grade grade ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.GRADE,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(grade.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }
    @RequestMapping(value = "/grades", params = {"page", "size","name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Grade> findAll(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestParam("name") String name,@RequestParam("page") int page, @RequestParam("size") int size) {

        // System.out.println(name);


        if(AuthProvider.isAuthorized(username,password, ModuleList.GRADE,AuthProvider.SELECT)) {

            List<Grade> grades = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Grade> gradestream = grades.stream();

            gradestream = gradestream.filter(e -> e.getName().startsWith(name));

            List<Grade> gradestream2 = gradestream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < gradestream2.size() ? start + size : gradestream2.size();
            Page<Grade> despage = new PageImpl<>(gradestream2.subList(start, end), PageRequest.of(page, size), gradestream2.size());

            return despage;
        }

        return null;

    }

    
}
