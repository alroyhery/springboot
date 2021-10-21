/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1;

import com.tugas.project1.models.User;
import com.tugas.project1.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS TUF
 */
@SpringBootTest
public class UserIntegrationWithoutMockingTests {
    
   @Autowired
   UserService service;
   
    @Test
    public void createUserTest() throws Exception {
       User user = new User();
       user.setEmail("asd@gmail.com");
        user.setName("abc");
        user.setPassword("123456");
        
        service.register(user);
        User checkUser = service.auth("asd@gmail.com", "123456");
        
        Assertions.assertEquals(user.getEmail(), checkUser.getEmail());
    }
    
   
    
    @Test
    public void createUserTestWithoutPassword() throws Exception {
       User user = new User();
       user.setEmail("qwe@gmail.com");
        user.setName("abc");
        user.setPassword("123456");
        
        service.register(user);
        User checkUser = service.auth("qwe@gmail.com", "123456");
        
        Assertions.assertEquals(user.getEmail(), checkUser.getEmail());
    }
    
    @Test
    public void createUserTestWithoutEmail() throws Exception {
       User user = new User();
       user.setEmail("asd@gmail.com");
        user.setName("abc");
        user.setPassword("123456");
        
        service.register(user);
        User checkUser = service.auth("asd@gmail.com", "123456");
        
        Assertions.assertEquals(user.getEmail(), checkUser.getEmail());
    }
    
    @Test
    public void createUserTestWithoutName() throws Exception {
       User user = new User();
       user.setEmail("asd@gmail.com");
        user.setName("abc");
        user.setPassword("123456");
        
        service.register(user);
        User checkUser = service.auth("asd@gmail.com", "123456");
        
        Assertions.assertEquals(user.getEmail(), checkUser.getEmail());
    }
    
     @Test
    public void createUserTestEmpty() throws Exception {
       User user = new User();
       user.setEmail("asd@gmail.com");
        user.setName("abc");
        user.setPassword("123456");
        
        service.register(user);
        User checkUser = service.auth("asd@gmail.com", "123456");
        
        Assertions.assertEquals(user.getEmail(), checkUser.getEmail());
    }
}
