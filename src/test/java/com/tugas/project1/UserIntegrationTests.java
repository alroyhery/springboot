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

    @Mock //MockBean
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
        try {
            User user = new User();
            user.setEmail("emptyName@mail.com");
            user.setName("");
            user.setPassword("test-strong-password");

            when(repository.save(user))
                    .thenThrow(new IllegalArgumentException("Name cannot be null!"));

            service.register(user);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Name cannot be null!", e.getMessage());
        }
    }

    @Test
    public void createUserTestWithEmptyEmail() throws Exception {
        try {
            User user = new User();
            user.setEmail("");
            user.setName("emptyEmail");
            user.setPassword("test-strong-password");

            when(repository.save(user))
                    .thenThrow(new IllegalArgumentException("Email cannot be null!"));

            service.register(user);
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Email cannot be null!", e.getMessage());
        }
    }

    @Test
    public void createUserTestWithSymbolsName() throws Exception {

        try {
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("~!@#$%^&*()_+-=<>,.?/");
            user.setPassword("123");

            when(repository.save(user))
                    .thenThrow(new IllegalArgumentException("Symbols on username is not allowed!"));

            service.register(user);

        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Symbols on username is not allowed!", e.getMessage());
        }
    }

    @Test
    public void createUserTestWithEmptyPassword() throws Exception {
        Throwable e = null;
        String message = null;

        try {
            User user = new User();
            user.setEmail("NoPW@mail.com");
            user.setName("NoPass");
            user.setPassword("");

            when(repository.save(user))
                    .thenThrow(new Exception("Password cannot be null!"));

            service.register(user);
        } catch (Exception ex) {
            e = ex;
            message = ex.getMessage();
        }

        Assertions.assertTrue(e instanceof Exception);

    }

    @Test
    public void createUserTestWithNumericName() throws Exception {

        try {
            User user = new User();
            user.setEmail("numericTest@mail.com");
            user.setName("1234567890");
            user.setPassword("123");

            when(repository.save(user))
                    .thenThrow(new IllegalArgumentException("Username without alphabets is not allowed!"));

            service.register(user);

        } catch (Exception e) {
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Username without alphabets is not allowed!", e.getMessage());
        }

    }

    @Test
    public void createUserTestWithSymbolsEmail() throws Exception {
        
        Throwable e = null;
        String message = null;
        
        try {
            User user = new User();
            user.setEmail("~!#$%^&*()<>?,/@mail.com");
            user.setName("Symbol Email");
            user.setPassword("123");

            when(repository.save(user))
                    .thenThrow(new Exception("Symbols other than . and @ are not allowed!"));

            service.register(user);
        } catch (Exception ex) {
            e = ex;
            message = ex.getMessage();
        }

        Assertions.assertTrue(e instanceof Exception);

    }

}
