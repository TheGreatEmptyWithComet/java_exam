package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String home() {

        if (customUserDetailsService.currentUserIsTeacher()) {
            return "redirect:/teacher/";
        }

        if (customUserDetailsService.currentUserIsStudent()) {
            return "redirect:/student/";
        }

        return "redirect:/";
    }
}
