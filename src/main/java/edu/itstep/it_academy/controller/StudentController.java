package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/")
    public String getStudentsGrades(Model model) {
//        StudentDTO studentDTO = studentService.getStudentsByDefaultSubject();
//        model.addAttribute("studentDTO", studentDTO);
        return "student-grades";
    }
}
