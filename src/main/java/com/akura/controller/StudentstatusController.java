package com.akura.controller;

import com.akura.dao.StudentstatusDao;
import com.akura.entity.Studentstatus;
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
public class StudentstatusController {

    @Autowired
    private StudentstatusDao dao;

    @RequestMapping(value = "/studentstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Studentstatus> studentstatus() {
        return dao.list();
    }


    @RequestMapping(value = "/studentstatuses", params = {"page", "size","name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Studentstatus> findAll(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {

        // System.out.println(name);


        if(AuthProvider.isAuthorized(username,password, ModuleList.STUDENTSTATUS,AuthProvider.SELECT)) {

            List<Studentstatus> studentstatuss = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Studentstatus> studentstatusstream = studentstatuss.stream();

            studentstatusstream = studentstatusstream.filter(e -> e.getName().startsWith(name));

            List<Studentstatus> studentstatusstream2 = studentstatusstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < studentstatusstream2.size() ? start + size : studentstatusstream2.size();
            Page<Studentstatus> despage = new PageImpl<>(studentstatusstream2.subList(start, end), PageRequest.of(page, size), studentstatusstream2.size());

            return despage;
        }

        return null;

    }


    @RequestMapping(value = "/studentstatuses", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Studentstatus studentstatus) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.STUDENTSTATUS,AuthProvider.INSERT)) {
            Studentstatus desname = dao.findByName(studentstatus.getName());
            if (desname != null)
                return "Error-Validation : Studentstatus Exists";
            else
                try {
                    dao.save(studentstatus);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/studentstatuses", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Studentstatus studentstatus) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.STUDENTSTATUS,AuthProvider.INSERT)) {
            Studentstatus desname = dao.findByName(studentstatus.getName());
            if (desname != null)
                return "Error-Validation : Studentstatus Exists";
            else
                try {
                    dao.save(studentstatus);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


    @RequestMapping(value = "/studentstatuses", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Studentstatus studentstatus ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.STUDENTSTATUS,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(studentstatus.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

}
