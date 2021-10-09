package com.tugas.project1.controllers;

import com.tugas.project1.interfaces.UserInterface;
import com.tugas.project1.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/login")
    public String index(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "login";
    }

    @PostMapping("/login")
    public String store(@ModelAttribute("user") User user,
            HttpServletRequest request, RedirectAttributes ra) throws Exception {
        HttpSession session = request.getSession(true);

        User obj = userInterface.auth(user.getEmail(), user.getPassword());

        if (obj == null) {
            ra.addFlashAttribute("error", "Invalid username or password!");
            return "redirect:/login";
        }

        session.setAttribute("id", obj.getId());
        session.setAttribute("email", obj.getEmail());
        session.setAttribute("name", obj.getName());
        session.setAttribute("loggedIn", true);

        return "redirect:/";
    }

}
