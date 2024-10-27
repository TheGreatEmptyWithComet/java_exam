package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.StudentDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.service.GradeService;
import edu.itstep.it_academy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TeacherController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping("/")
    public String getStudentsGrades(Model model) {
        StudentDTO studentDTO = studentService.getStudentsByDefaultSubject();
        model.addAttribute("studentDTO", studentDTO);
        return "students-grades";
    }

    @GetMapping("/getStudentsGrades")
    public String getStudentsGrades(@RequestParam("subjectId") Long subjectId, Model model) {
        StudentDTO studentDTO = studentService.getStudentsBySubjectId(subjectId);
        model.addAttribute("studentDTO", studentDTO);
        return "students-grades";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("studentDTO") StudentDTO studentDTO, Model model) {
        System.out.println("POST getStudentsGrades");
        studentDTO = studentService.getStudentsBySubjectId(studentDTO.getSubjectId());
        model.addAttribute("studentDTO", studentDTO);
        return "students-grades";
    }

    @PostMapping("/createGrade")
    public String createGrade(@ModelAttribute("gradeDTO") GradeDTO gradeDTO, Model model) {
        gradeDTO = gradeService.fillGradeDTO(gradeDTO);
        model.addAttribute("gradeDTO", gradeDTO);
        return "grade-form";
    }

    @PostMapping("/saveGrade")
    public String saveGrade(@ModelAttribute("gradeDTO") GradeDTO gradeDTO, Model model) {
        gradeService.saveGrade(gradeDTO);
        return "redirect:/getStudentsGrades?subjectId=" + gradeDTO.getSubjectId();
    }

    @GetMapping("/updateGrade")
    public String updateGrade(@RequestParam("gradeId") long gradeId, Model model) {
        GradeDTO gradeDTO = gradeService.getGradeDTOByGradeId(gradeId);
        model.addAttribute("gradeDTO", gradeDTO);
        System.out.println(gradeDTO);
        return "grade-form";
    }
}
