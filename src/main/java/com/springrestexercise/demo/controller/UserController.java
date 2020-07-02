package com.springrestexercise.demo.controller;

import com.springrestexercise.demo.entity.User;
import com.springrestexercise.demo.entity.UserRepository;
import com.springrestexercise.demo.exception.UserErrorResponse;
import com.springrestexercise.demo.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class UserController {

    List<User> users;
    @PostConstruct
    public void loadData(){
        users = new ArrayList<>();
        users.add(new User("milan","gowda","milano","milan@gmail.cm","123456"));
        users.add(new User("rahul","gowda","rahulo","rahul@gmail.cm","784484"));
        users.add(new User("nikhil","gowda","nikhilno","nikhi@gmail.cm","123456"));
        users.add(new User("vishwa","kumar","vishwo","viahwa@gmail.cm","123456"));
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return users ;
    }

    @GetMapping("/users/{userid}")
    public User getUser(@PathVariable int userid){
        if(userid<=0||userid>users.size())
            throw new UserNotFoundException("User not Found "+userid);
        return users.get(userid-1);
        }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> exceptionHandler(UserNotFoundException e){
        UserErrorResponse error= new UserErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public String createUser(User user){
        return "createUser";
    }

    @PutMapping
    public String updateUser(){
        return "updateUser";
    }

    @DeleteMapping
    public String deleteUser(){
        return "deleteUser";
    }

}
