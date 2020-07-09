package com.examplerest.demo.com;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@ApiModel(value = "User" ,description = "User details for User Registration")
public class User {
    public User() {
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "User's firstName",required = true)
    private String firstName;
    @ApiModelProperty(value = "User's username",required = true)
    private String username;
    @ApiModelProperty(value = "User's lastName",required = true)
    private String lastName;
    @ApiModelProperty(value = "User's email",required = true)
    private String email;
    @ApiModelProperty(value = "User's password",required = true)

    private String password;

    public User( String firstName, String username , String email, String password, String lastName) {
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

}
