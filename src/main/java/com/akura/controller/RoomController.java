package com.akura.controller;

import com.akura.dao.RoomDao;
import com.akura.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomDao dao;


    @RequestMapping(value = "/rooms/list", method = RequestMethod.GET, produces = "application/json")
    public List<Room> room() {
        return dao.list();
    }

    @RequestMapping(value = "/rooms/list/bybranch", params = {"branchid"},method = RequestMethod.GET, produces = "application/json")
    public List<Room> employee(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, Integer branchid) {
        if (AuthProvider.isUser(username, password))
            return dao.listByBranch(branchid);
        else return null;

    }

}
