package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.StudentsOutDTO;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.TeacherRepository;
import edu.itstep.it_academy.service.GradeService;
import edu.itstep.it_academy.service.StudentService;
import edu.itstep.it_academy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//        List<Student> students = teacherRepository.getStudentsByDefaultSubject();
//        List<Subject> subjects =teacherRepository.getSubjects();
//        model.addAttribute("students", students);
//        model.addAttribute("subjects", subjects);
        System.out.println("getStudentsGrades start ");
        int result = gradeService.test();
        System.out.println("test = " + result);
        System.out.println(studentService);
        System.out.println(studentRepository);
        System.out.println(gradeService);
        System.out.println("getStudentsGrades before ");
        StudentsOutDTO studentsOutDTO = studentService.getStudentsByDefaultSubject();
        System.out.println("getStudentsGrades after ");
        model.addAttribute("studentsOutDTO", studentsOutDTO);
        return "students-grades";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("studentsOutDTO") StudentsOutDTO studentsOutDTO, Model model) {
        List<Student> students = teacherRepository.getStudentsBySubjectId(studentsOutDTO.getSubjectId());
        List<Subject> subjects = teacherRepository.getSubjects();
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "students-grades";
    }
}
