//package edu.itstep.it_academy.service;
//
//import edu.itstep.it_academy.entity.Student;
//import edu.itstep.it_academy.entity.Teacher;
//import edu.itstep.it_academy.repository.StudentRepository;
//import edu.itstep.it_academy.repository.TeacherRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Optional;
//
////@Service
////public class UserDetailsServiceImpl implements UserDetailsService {
////
////    @Autowired
////    private TeacherRepository teacherRepository;
////
////    @Autowired
////    private StudentRepository studentRepository;
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Teacher teacher = teacherRepository.findByUsername(username);
////        if (teacher != null) {
////            return new org.springframework.security.core.userdetails.User(
////                    teacher.getUsername(),
////                    teacher.getPassword(),
////                    Collections.singletonList(new SimpleGrantedAuthority(teacher.getRole()))
////            );
////        }
////
////        Student student = studentRepository.findByUsername(username);
////        if (student != null) {
////            return new org.springframework.security.core.userdetails.User(
////                    student.getUsername(),
////                    student.getPassword(),
////                    Collections.singletonList(new SimpleGrantedAuthority(student.getRole()))
////            );
////        }
////
////        throw new UsernameNotFoundException("User not found with username: " + username);
////    }
////}
