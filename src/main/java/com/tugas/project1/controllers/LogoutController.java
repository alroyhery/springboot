package com.tugas.project1.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String index(HttpServletRequest request, RedirectAttributes ra) {
        HttpSession session = request.getSession(true);
        
        session.removeAttribute("id");
        session.removeAttribute("name");
        session.removeAttribute("email");
        session.removeAttribute("loggedIn");
        session.invalidate();
        
        ra.addFlashAttribute("success", "Berhasil keluar dari sistem!");
        return "redirect:/login";
    }
}