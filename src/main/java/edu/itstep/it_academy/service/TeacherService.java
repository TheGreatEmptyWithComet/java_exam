package edu.itstep.it_academy.service;

import edu.itstep.it_academy.entity.Teacher;
import edu.itstep.it_academy.exception.UserNotFoundException;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public Teacher getCurrentTeacher(){
        String username = customUserDetailsService.getCurrentUserUsername();
        return teacherRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
    }
}
