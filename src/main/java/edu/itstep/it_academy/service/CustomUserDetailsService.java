package edu.itstep.it_academy.service;

import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Teacher;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> teacherOpt = teacherRepository.findByUsername(username);
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            return User.withUsername(teacher.getUsername())
                    .password(teacher.getPassword()) // Hashed password
                    .authorities(new SimpleGrantedAuthority(teacher.getRole()))
                    .build();
        }

        Optional<Student> studentOpt = studentRepository.findByUsername(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return User.withUsername(student.getUsername())
                    .password(student.getPassword()) // Hashed password
                    .authorities(new SimpleGrantedAuthority(student.getRole()))
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }

    public String getCurrentUserUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean currentUserIsTeacher(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        return roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_TEACHER"));
    }

    public boolean currentUserIsStudent(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        return roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_STUDENT"));
    }
}