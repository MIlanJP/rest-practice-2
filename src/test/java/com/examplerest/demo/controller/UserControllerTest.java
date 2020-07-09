package com.examplerest.demo.controller;

import com.examplerest.demo.DemoApplication;
import com.examplerest.demo.com.User;
import com.examplerest.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK,classes=DemoApplication.class)
public class UserControllerTest {

    private final String CONTEXT_PATH="/users";


    @MockBean
    UserService userService;

    @InjectMocks
    UserController userController;

    private MockMvc mockMvc;

    List<User> listOfUsers;

    String json;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        listOfUsers=new ArrayList<User>();
        listOfUsers.add(new User("Mockito","mock","milan@gamil.com","12456","gowda"));
        listOfUsers.add(new User("rakesh","rakes","rak@gamil.com","12456","kumar"));
        listOfUsers.add(new User("vivek","vivk","vik@gamil.com","12456","kuamr"));
        listOfUsers.add(new User("Hari","hari","kumar@gamil.com","12456","kumar"));
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
        Gson gson=new Gson();
         json=gson.toJson(listOfUsers);
    }

    @Test
    public void givenListOfUsers_whenParsed_ShouldReturnAsJsonInResponseBodyPositiveTesting() throws Exception {
        when(userService.listAll()).thenReturn(listOfUsers);
        this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json(json)).andReturn() ;


    }

    @Test
    public void givenListOfUsers_whenParsed_ShouldReturnAsJsonInResponseBodyNegativeTesting() throws Exception {
        when(userService.listAll()).thenReturn(listOfUsers);
        this.mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("[{\"id\":null,\"firstName\":\"Mockito\"" +
                ",\"username\":\"mock\",\"lastName\":\"gowda\",\"email\":\"milan@gamil.com\",\"password\":" +
                "\"12456\"},{\"id\":null,\"firstName\":\"rakesh\",\"username\":\"rakes\",\"lastName\":\"kumar\"," +
                "\"email\":\"rak@gamil.com\",\"password\":\"12456\"},{\"id\":null,\"firstName\":\"vivek\"," +
                "\"username\":\"vivk\",\"lastName\":\"kuamr\",\"email\":\"vik@gamil.com\",\"password\":\"12456\"}" +
                ",{\"id\":null,\"firstName\":\"Hari\",\"username\":\"hari\",\"lastName\":\"kumar\",\"email\":" +
                "\"kumar@gamil.com\",\"password\":\"12456\"}]")) ;
    }

    @Test
    public void name() {
    }
}
