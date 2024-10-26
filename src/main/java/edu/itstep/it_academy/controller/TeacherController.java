package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.StudentsOutDTO;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.TeacherRepository;
import edu.itstep.it_academy.service.GradeService;
import edu.itstep.it_academy.service.StudentService;
import edu.itstep.it_academy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TeacherController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeService gradeService;

    @GetMapping("/")
    public String getStudentsGrades(Model model) {
        StudentsOutDTO studentsOutDTO = studentService.getStudentsByDefaultSubject();
        model.addAttribute("studentsOutDTO", studentsOutDTO);
        return "students-grades";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("studentsOutDTO") StudentsOutDTO studentsOutDTO, Model model) {
        System.out.println(studentsOutDTO.getSubjectId());
        studentsOutDTO = studentService.getStudentsBySubjectId(studentsOutDTO.getSubjectId());
        model.addAttribute("studentsOutDTO", studentsOutDTO);
        return "students-grades";
    }
}
