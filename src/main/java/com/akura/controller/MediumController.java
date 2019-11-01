package com.akura.controller;

import com.akura.dao.MediumDao;
import com.akura.entity.Medium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MediumController {

    @Autowired
    private MediumDao dao;


    @RequestMapping(value = "/mediums/list", method = RequestMethod.GET, produces = "application/json")
    public List<Medium> medium() { return dao.list();
    }

    @RequestMapping(value = "/mediums/list/bysubject", params = {"subjectid"},method = RequestMethod.GET, produces = "application/json")
    public List<Medium> medium(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, Integer subjectid) {
        if (AuthProvider.isUser(username, password))
            return dao.listBySubject(subjectid);
        else return null;
    }
}
