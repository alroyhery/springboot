package com.tugas.project1.controllers;

import com.tugas.project1.interfaces.UserInterface;
import com.tugas.project1.models.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/register")
    public String index(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public String store(@ModelAttribute("user") User user,
            HttpServletRequest request) throws Exception {

        userInterface.register(user);

        return "redirect:/login";
    }

}
