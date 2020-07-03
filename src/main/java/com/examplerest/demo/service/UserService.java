package com.examplerest.demo.service;

import com.examplerest.demo.com.User;
import com.examplerest.demo.exception.UserErrorResponse;
import com.examplerest.demo.exception.UserNotFoundException;
import com.examplerest.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getById(Integer id){
        User user=null;
        try {
            user= userRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new UserNotFoundException("User with Id "+id+" not found");
        }
        return user;
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> addeException(UserNotFoundException e){
        UserErrorResponse error = new UserErrorResponse(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    public void deleteById(Integer id){
         userRepository.deleteById(id);
    }

    public User getByUsername(String username) {
        List<User> users=userRepository.findAll();
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().get();
    }
}
