package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

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
