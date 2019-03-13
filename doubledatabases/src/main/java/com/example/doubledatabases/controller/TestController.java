package com.example.doubledatabases.controller;

import com.example.doubledatabases.model.User1DTO;
import com.example.doubledatabases.model.User2DTO;
import com.example.doubledatabases.model.UserDTO;
import com.example.doubledatabases.service.User1Service;
import com.example.doubledatabases.service.User2Service;
import com.example.doubledatabases.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;
    @Autowired
    private User1Service user1Service;
    @Autowired
    private User2Service user2Service;

    @RequestMapping(value = "/queryUsers" ,method = RequestMethod.POST)
    public List<UserDTO> queryUsers(){
        return userService.queryUser();
    }
    @RequestMapping(value = "/queryUsers1" ,method = RequestMethod.POST)
    public List<User1DTO> queryUsers1(){
        return user1Service.queryUser();
    }
    @RequestMapping(value = "/queryUsers2" ,method = RequestMethod.POST)
    public List<User2DTO> queryUsers2(){
        return user2Service.queryUser2();
    }
}
