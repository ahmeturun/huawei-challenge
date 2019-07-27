package com.example.todo.todo.controller;

import com.example.todo.todo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/Register")
    public String register(String name, String password) {
        return userService.register(name, password);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/Login")
    public String login(String name, String password) {
        return userService.login(name, password);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/Logout")
    public void logout(String name) {
        userService.logout(name);
    }
}