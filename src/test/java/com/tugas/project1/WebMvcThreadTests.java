package com.tugas.project1;

import com.tugas.project1.models.Thread;
import com.tugas.project1.models.Category;
import com.tugas.project1.models.User;
import java.util.HashMap;
import net.bytebuddy.utility.RandomString;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 *
 * @author Lenovo
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcThreadTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testPostThread() throws Exception {

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String random = RandomString.make(2).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Test-"+random);
        user.setPassword(password);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));


        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/thread/create")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tuliskan judul threadmu")));
        
        String title = "title-" + RandomString.make(10).toLowerCase();
        String content = "cont-" + RandomString.make(50).toLowerCase();
        
        Category cat =  new Category();
        cat.setId(1);
        
        Thread thread = new Thread();
        thread.setTitle(title);
        thread.setCategory(cat);
        thread.setContent(content);
        thread.setUser(user);
        
        mockMvc.perform(post("/thread/store")
                .sessionAttrs(sessionattr)
                .flashAttr("thread", thread))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
    
    @Test
    public void testPostWithoutTitle() throws Exception{       
        
        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String random = RandomString.make(2).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Test-"+random);
        user.setPassword(password);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));


        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();

        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/thread/create")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tuliskan judul threadmu")));
        
        String title = "";
        String content = "cont-" + RandomString.make(50).toLowerCase();
        
        Category cat =  new Category();
        Thread thread = new Thread();
        
        cat.setId(1);
        thread.setTitle(title);
        thread.setCategory(cat);
        thread.setContent(content);
        thread.setUser(user);
        
        mockMvc.perform(post("/thread/store")
        .flashAttr("thread", thread))
        .andExpect(status().is3xxRedirection())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/thread/store")) //Failures: WebMvcThreadTests.testPostWithoutTitle:156 Redirected URL expected:</thread/store> but was:</login>
        .andExpect(MockMvcResultMatchers
                .flash().attributeExists("danger")
        )
        .andExpect(
                MockMvcResultMatchers
                        .flash()
                        .attribute("danger", "Title of the thread cannot be null!")
        );    
    }
}