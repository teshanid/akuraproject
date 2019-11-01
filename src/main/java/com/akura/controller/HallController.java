package com.akura.controller;

import com.akura.dao.HallDao;
import com.akura.entity.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HallController {

    @Autowired
    private HallDao dao;


    @RequestMapping(value = "/halls/list", method = RequestMethod.GET, produces = "application/json")
    public List<Hall> room() {
        return dao.list();
    }

    @RequestMapping(value = "/halls/list/bybranch", params = {"branchid"},method = RequestMethod.GET, produces = "application/json")
    public List<Hall> employee(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, Integer branchid) {
        if (AuthProvider.isUser(username, password))
            return dao.listByBranch(branchid);
        else return null;

    }

}
