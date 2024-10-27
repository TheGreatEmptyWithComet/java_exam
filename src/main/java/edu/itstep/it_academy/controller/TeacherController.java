package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.StudentDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.service.GradeService;
import edu.itstep.it_academy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;

    @GetMapping("/")
    public String getStudentsGrades(Model model) {
        StudentDTO studentDTO = studentService.getStudentsByDefaultSubject();
        model.addAttribute("studentDTO", studentDTO);
        return "teacher-students";
    }

    @GetMapping("/getStudentsGrades")
    public String getStudentsGrades(@RequestParam("subjectId") Long subjectId, Model model) {
        StudentDTO studentDTO = studentService.getStudentsBySubjectId(subjectId);
        model.addAttribute("studentDTO", studentDTO);
        return "teacher-students";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("studentDTO") StudentDTO studentDTO, Model model) {
        studentDTO = studentService.getStudentsBySubjectId(studentDTO.getSubjectId());
        model.addAttribute("studentDTO", studentDTO);
        return "teacher-students";
    }

    @PostMapping("/createGrade")
    public String createGrade(@ModelAttribute("gradeDTO") GradeDTO gradeDTO, Model model) {
        gradeDTO = gradeService.fillGradeDTO(gradeDTO);
        model.addAttribute("gradeDTO", gradeDTO);
        return "grade-form";
    }

    @PostMapping("/saveGrade")
    public String saveGrade(@ModelAttribute("gradeDTO") GradeDTO gradeDTO) {
        gradeService.saveGrade(gradeDTO);
        return "redirect:/teacher/getStudentsGrades?subjectId=" + gradeDTO.getSubjectId();
    }

    @GetMapping("/updateGrade")
    public String updateGrade(@RequestParam("gradeId") long gradeId, Model model) {
        GradeDTO gradeDTO = gradeService.getGradeDTOByGradeId(gradeId);
        model.addAttribute("gradeDTO", gradeDTO);
        return "grade-form";
    }

    @GetMapping("/deleteGrade")
    public String deleteGrade(@RequestParam("gradeId") long gradeId) {
        Grade grade = gradeService.getGradeById(gradeId);
        gradeService.deleteGradeById(gradeId);
        return "redirect:/teacher/getStudentsGrades?subjectId=" + grade.getSubject().getId();
    }
}
