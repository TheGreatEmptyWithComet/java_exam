package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/")
    public String getStudentsGrades(Model model){
        Subject subject = teacherRepository.getSubjects().get(0);
        List<Grade> grades = teacherRepository.getStudentsGrades(subject);
        model.addAttribute("grades",grades);

        return "students-grades";
    }
}
