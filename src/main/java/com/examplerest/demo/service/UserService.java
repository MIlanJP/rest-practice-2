package com.examplerest.demo.service;

import com.examplerest.demo.com.User;
import com.examplerest.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Transactional
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
            user= userRepository.findById(id).get();

        return user;
    }



    public void deleteById(Integer id){
         userRepository.deleteById(id);
    }

    public User getByUsername(String username) {
        List<User> users=userRepository.findAll();
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().get();
    }
}
