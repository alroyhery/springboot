/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.controllers;

import com.tugas.project1.interfaces.CategoryInterface;
import com.tugas.project1.interfaces.CommentInterface;
import com.tugas.project1.models.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.tugas.project1.interfaces.ThreadInterface;
import com.tugas.project1.models.Category;
import com.tugas.project1.models.Comment;
import com.tugas.project1.models.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS TUF
 */
@Controller
public class MainController {
    @Autowired
    private ThreadInterface threadInterface;

    
    @Autowired
    private CategoryInterface categoryInterface;


    @GetMapping("/")
    public String index(Model model,  HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        
        long user_id = (long) session.getAttribute("id");
        
        List<Thread> threads = threadInterface.findByUserId(user_id);
        
        model.addAttribute("list", threadInterface.getAll());

        
        model.addAttribute("threads", threads);


        return "index";
    }

    @GetMapping("/thread/create")
    public String create(Model model) {
        
       List<Category> category = categoryInterface.getAll();
        model.addAttribute("category", category);
        
        Thread thread = new Thread();
        model.addAttribute("thread", thread);

        return "create";
    }

    @PostMapping("/thread/store")
    public String store(@ModelAttribute("thread") Thread thread, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        
        User user = new User();
        user.setId((long) session.getAttribute("id"));
        
        thread.setUser(user);

        threadInterface.store(thread);
        return "redirect:/";
    }
    
   
    
    @GetMapping("/thread/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model) {
       List<Category> category = categoryInterface.getAll();
        model.addAttribute("category", category);
        
        Thread thread = threadInterface.getById(id);

        model.addAttribute("thread", thread);
        return "edit";
    }

    @PostMapping("/thread/{id}/delete")
    public String delete(@PathVariable(value = "id") long id) {
       threadInterface.delete(id);
        return "redirect:/";
    }
}
