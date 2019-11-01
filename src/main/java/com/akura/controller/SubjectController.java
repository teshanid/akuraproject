package com.akura.controller;

import com.akura.dao.SubjectDao;
import com.akura.entity.Subject;
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
public class SubjectController {

    @Autowired
    private SubjectDao dao;

    @RequestMapping(value = "/subjects/list", method = RequestMethod.GET, produces = "application/json")
    public List<Subject> subject() {
        return dao.list();
    }

    @RequestMapping(value = "/subjects/list/bygrade", params = {"gradeid"},method = RequestMethod.GET, produces = "application/json")
    public List<Subject> subject(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, Integer gradeid) {
        if (AuthProvider.isUser(username, password))
            return dao.listByGrade(gradeid);
        else return null;
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Subject subject) {

        if(AuthProvider.isAuthorized(username,password, ModuleList.SUBJECT,AuthProvider.INSERT)) {
            Subject grname = dao.findByName(subject.getName());
            if (grname != null)
                return "Error-Validation : Subject Exists";
            else
                try {
                    dao.save(subject);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/subjects", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Subject subject) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.SUBJECT,AuthProvider.INSERT)) {
            Subject grname = dao.findByName(subject.getName());
            if (grname != null)
                return "Error-Validation : Subject Exists";
            else
                try {
                    dao.save(subject);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


    @RequestMapping(value = "/subjects", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Subject subject ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.DESIGNATION,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(subject.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }
    @RequestMapping(value = "/subjects", params = {"page", "size","name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Subject> findAll(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {

        // System.out.println(name);


        if(AuthProvider.isAuthorized(username,password, ModuleList.DESIGNATION,AuthProvider.SELECT)) {

            List<Subject> subjects = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Subject> subjectstream = subjects.stream();

            subjectstream = subjectstream.filter(e -> e.getName().startsWith(name));

            List<Subject> subjectstream2 = subjectstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < subjectstream2.size() ? start + size : subjectstream2.size();
            Page<Subject> despage = new PageImpl<>(subjectstream2.subList(start, end), PageRequest.of(page, size), subjectstream2.size());

            return despage;
        }

        return null;

    }


}
