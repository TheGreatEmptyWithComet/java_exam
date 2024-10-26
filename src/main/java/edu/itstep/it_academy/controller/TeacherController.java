package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.SubjectFormDTO;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/")
    public String getStudentsGrades(Model model) {
        List<Student> students = teacherRepository.getStudentsByDefaultSubject();
        List<Subject> subjects =teacherRepository.getSubjects();
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        model.addAttribute("subjectFormDTO", new SubjectFormDTO());
        return "students-grades";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("subjectFormDTO") SubjectFormDTO subjectFormDTO, Model model) {
        List<Student> students = teacherRepository.getStudentsBySubjectId(subjectFormDTO.getSubjectId());
        List<Subject> subjects =teacherRepository.getSubjects();
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "students-grades";
    }
}
