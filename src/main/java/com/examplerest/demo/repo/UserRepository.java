package com.examplerest.demo.repo;

import com.examplerest.demo.com.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
