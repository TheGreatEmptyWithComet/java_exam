package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.StudentTeacherDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.service.GradeService;
import edu.itstep.it_academy.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        StudentTeacherDTO studentTeacherDTO = studentService.getStudentsByDefaultSubject();
        model.addAttribute("studentTeacherDTO", studentTeacherDTO);
        return "teacher-students";
    }

    @GetMapping("/getStudentsGrades")
    public String getStudentsGrades(@RequestParam("subjectId") Long subjectId, Model model) {
        StudentTeacherDTO studentTeacherDTO = studentService.getStudentsBySubjectId(subjectId);
        model.addAttribute("studentTeacherDTO", studentTeacherDTO);
        return "teacher-students";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("studentDTO") StudentTeacherDTO studentTeacherDTO, Model model) {
        studentTeacherDTO = studentService.getStudentsBySubjectId(studentTeacherDTO.getSubjectId());
        model.addAttribute("studentTeacherDTO", studentTeacherDTO);
        return "teacher-students";
    }

    @PostMapping("/createGrade")
    public String createGrade(@ModelAttribute("gradeDTO") GradeDTO gradeDTO, Model model) {
        gradeDTO = gradeService.fillGradeDTO(gradeDTO);
        model.addAttribute("gradeDTO", gradeDTO);
        return "grade-form";
    }

    @PostMapping("/saveGrade")
    public String saveGrade(@Valid @ModelAttribute("gradeDTO") GradeDTO gradeDTO, BindingResult result,Model model) {
        System.out.println(gradeDTO);
        if (result.hasErrors()) {
            System.out.println(gradeDTO);
            model.addAttribute("gradeDTO", gradeDTO);
            return "grade-form";
        }
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
