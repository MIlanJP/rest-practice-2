package com.examplerest.demo.service;

import com.examplerest.demo.com.User;
import com.examplerest.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userRepository.findById(id).get();
    }

    public void deleteById(Integer id){
         userRepository.deleteById(id);
    }

