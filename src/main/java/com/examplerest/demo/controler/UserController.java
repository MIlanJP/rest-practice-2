package com.examplerest.demo.controler;

import com.examplerest.demo.com.User;
import com.examplerest.demo.exception.UserErrorResponse;
import com.examplerest.demo.exception.UserNotFoundException;
import com.examplerest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
    public User getByUsername(@PathVariable String username){
        return userService.getByUsername(username);
    }

    @GetMapping("/users/id/{id}")
    public User getById(@PathVariable Integer id){
        try{
        return userService.getById(id);
    }catch(NoSuchElementException e){
        throw new UserNotFoundException("User with Id "+id+" not found");
        }
    }

//    get firstname of the user from username
    @GetMapping("/users/fullname/{username}")
    public String getFullNameByPathVariable(@PathVariable String username){
        User user= userService.getByUsername(username);
        return user.getFirstName()+" "+user.getLastName();
    }

    @GetMapping("/users/fullname")
    public String getFullNameByRequestParam(@RequestParam String username){
        User user= userService.getByUsername(username);
        return user.getFirstName()+" "+user.getLastName();
    }

    @GetMapping("/users/printfullname")
    public String printFirstNameAndSecondName(@RequestParam String firstName, @RequestParam String secondName){
        return  firstName+" "+secondName;
    }


    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        user.setId(0);
        userService.save(user);
    }

    @DeleteMapping("/users/id/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }
}
