/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugas.project1.controllers;

import com.tugas.project1.models.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.tugas.project1.interfaces.ThreadInterface;

/**
 *
 * @author ASUS TUF
 */
@Controller
public class MainController {
    @Autowired
    private ThreadInterface threadInterface;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", threadInterface.getAll());
        return "index";
    }

    @GetMapping("/thread/create")
    public String create(Model model) {
        
        Thread thread = new Thread();
        model.addAttribute("thread", thread);
        
        return "create";
    }

    @PostMapping("/thread/store")
    public String store(@ModelAttribute("thread") Thread inventory) {
        threadInterface.store(inventory);
        return "redirect:/";
    }
    
    @GetMapping("/thread/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model) {
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
