package com.akura.controller;

import com.akura.dao.GuardiantypeDao;
import com.akura.entity.Guardiantype;
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
public class GuardiantypeController {

    @Autowired
    private GuardiantypeDao dao;

    @RequestMapping(value = "/guardiantypes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Guardiantype> guardiantype() {
        return dao.list();
    }


    @RequestMapping(value = "/guardiantypes", params = {"page", "size", "name"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Guardiantype> findAll(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {

        // System.out.println(name);


        if (AuthProvider.isAuthorized(username, password, ModuleList.GUARDIANTYPE, AuthProvider.SELECT)) {

            List<Guardiantype> guardiantypes = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Guardiantype> guardiantypestream = guardiantypes.stream();

            guardiantypestream = guardiantypestream.filter(e -> e.getName().startsWith(name));

            List<Guardiantype> guardiantypestream2 = guardiantypestream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < guardiantypestream2.size() ? start + size : guardiantypestream2.size();
            Page<Guardiantype> despage = new PageImpl<>(guardiantypestream2.subList(start, end), PageRequest.of(page, size), guardiantypestream2.size());

            return despage;
        }

        return null;

    }


    @RequestMapping(value = "/guardiantypes", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Guardiantype guardiantype) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.GUARDIANTYPE, AuthProvider.INSERT)) {
            Guardiantype desname = dao.findByName(guardiantype.getName());
            if (desname != null)
                return "Error-Validation : Guardiantype Exists";
            else
                try {
                    dao.save(guardiantype);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/guardiantypes", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Guardiantype guardiantype) {

        if (AuthProvider.isAuthorized(username, password, ModuleList.GUARDIANTYPE, AuthProvider.INSERT)) {
            Guardiantype desname = dao.findByName(guardiantype.getName());
            if (desname != null)
                return "Error-Validation : Guardiantype Exists";
            else
                try {
                    dao.save(guardiantype);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


    @RequestMapping(value = "/guardiantypes", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @RequestBody Guardiantype guardiantype) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.GUARDIANTYPE, AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(guardiantype.getId()));
                return "0";
            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }



}
