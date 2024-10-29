package edu.itstep.it_academy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class LoginController {

    @GetMapping("/")
    public String yourMethod() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Get the roles (authorities) of the current user
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        boolean isTeacher = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_TEACHER"));
        if (isTeacher) {
            return "redirect:/teacher/";
        }

        boolean isStudent = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_STUDENT"));
        if (isStudent) {
            return "redirect:/student/";
        }

        return "redirect:/";
    }
}
