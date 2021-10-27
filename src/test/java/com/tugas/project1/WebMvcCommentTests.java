/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1;



import com.tugas.project1.models.Thread;
import com.tugas.project1.models.Category;
import com.tugas.project1.models.User;
import com.tugas.project1.models.Comment;
import java.util.HashMap;
import net.bytebuddy.utility.RandomString;
import java.util.concurrent.ThreadLocalRandom;
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
 * @author ASUS TUF
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcCommentTests {
    
    
     @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testPostComment() throws Exception {
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
        
         mockMvc.perform(get("/thread/27/postdetail")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("AUTHOR")));
      
        
        mockMvc.perform(get("/thread/27/comment")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Comment")));
        
        
        String content = "comment-" + RandomString.make(50).toLowerCase();
        
        Thread thread = new Thread();
        Comment comment = new Comment();
        
        comment.setContent(content);
        comment.setUser(user);
        thread.setId(27);
        
        
        mockMvc.perform(post("/thread/27/comment/store")
                .sessionAttrs(sessionattr)
                .flashAttr("comment", comment))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/thread/27/postdetail"))
                .andDo(print());
    }
}
