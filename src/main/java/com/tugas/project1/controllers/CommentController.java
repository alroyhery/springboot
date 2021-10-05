/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.controllers;

import com.tugas.project1.interfaces.CommentInterface;
import com.tugas.project1.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ASUS TUF
 */
@Controller
public class CommentController {
     @Autowired
    private CommentInterface commentInterface;
     
     @GetMapping("/comment/create")
    public String create(Model model) {
        
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        
        return "comment";
    }
     
     
     
     @PostMapping("/comment/store")
    public String send(@ModelAttribute("comment") Comment comment) {
        commentInterface.send(comment);
        return "redirect:/";
    }
     
     
}
