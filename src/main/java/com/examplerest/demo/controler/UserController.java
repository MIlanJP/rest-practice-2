package com.examplerest.demo.controler;

import com.examplerest.demo.com.User;
import com.examplerest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> list(){
        return userService.listAll();
    }

    @GetMapping("/users/{username}")
    public User getUser(String username){
        userService.getByUsername(username);
    }




}
