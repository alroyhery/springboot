/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1;

/**
 *
 * @author ASUS TUF
 */
import com.tugas.project1.models.User;
import com.tugas.project1.repositories.UserRepository;
import com.tugas.project1.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class UserIntegrationTests {
    @InjectMocks
    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;
    
    @Test
    public void createUserTest() throws Exception {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setName("Test1");
        user.setPassword("test-strong-password");

        when(repository.save(user)).thenReturn(user);
        service.register(user);

        when(repository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = this.repository.findByEmail("test@mail.com");

        Assertions.assertEquals(user, checkUser);
    }
    
     @Test
    public void createUserTestWithEmptyName() throws Exception {
        
        User user = new User();
        user.setEmail("test1@mail.com");
        user.setName("");
        user.setPassword("test-strong-password");

        when(repository.save(user)).thenReturn(user);
        service.register(user);

        when(repository.findByEmail("test1@mail.com")).thenReturn(user);

        User checkUser = this.repository.findByEmail("test1@mail.com");

        Assertions.assertEquals(user, checkUser);
    }
    
    @Test
    public void createUserTestWithEmptyEmail() throws Exception {
        Throwable e = null;
        String message = null;
  
        try {
            User user = new User();
            user.setEmail("");
            user.setName("abc");
            user.setPassword("test-strong-password");

            when(repository.save(user))
                    .thenThrow(new Exception("Email cannot be null!"));
            
            service.register(user);
        } catch (Exception ex) {
            e = ex;
            message = ex.getMessage();
        }
        
        Assertions.assertTrue(e instanceof Exception);
//       Assertions.assertEquals("Email cannot be null!", message);
    }
    
     @Test
    public void createUserTestWithEmptyPassword() throws Exception {
        Throwable e = null;
        String message = null;
  
        try {
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("abc");
            user.setPassword("");

            when(repository.save(user))
                    .thenThrow(new Exception("Password cannot be null!"));
            
            service.register(user);
        } catch (Exception ex) {
            e = ex;
            message = ex.getMessage();
        }
        
        Assertions.assertTrue(e instanceof Exception);
//        Assertions.assertEquals("Password cannot be null!", message);
    }
     @Test
     public void createUserTestWithEmptyAll() throws Exception {
        Throwable e = null;
        String message = null;
  
        try {
            User user = new User();
            user.setEmail("");
            user.setName("");
            user.setPassword("");

            when(repository.save(user))
                    .thenThrow(new Exception("Everything cannot be null!"));
            
            service.register(user);
        } catch (Exception ex) {
            e = ex;
            message = ex.getMessage();
        }
        
        Assertions.assertTrue(e instanceof Exception);
//        Assertions.assertEquals("Everything cannot be null!", message);
    }
}
