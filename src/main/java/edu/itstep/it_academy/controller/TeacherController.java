package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.TeacherStudentsDTO;
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
        TeacherStudentsDTO teacherStudentsDTO = studentService.getStudentsGradesByDefaultSubject();
        model.addAttribute("teacherStudentsDTO", teacherStudentsDTO);
        return "teacher-students";
    }

    @GetMapping("/getStudentsGrades")
    public String getStudentsGrades(@RequestParam("subjectId") Long subjectId, Model model) {
        TeacherStudentsDTO teacherStudentsDTO = studentService.getStudentsBySubjectId(subjectId);
        model.addAttribute("teacherStudentsDTO", teacherStudentsDTO);
        return "teacher-students";
    }

    @PostMapping("/getStudentsGrades")
    public String getStudentsGrades(@ModelAttribute("studentDTO") TeacherStudentsDTO teacherStudentsDTO, Model model) {
        teacherStudentsDTO = studentService.getStudentsBySubjectId(teacherStudentsDTO.getSubjectId());
        model.addAttribute("teacherStudentsDTO", teacherStudentsDTO);
        return "teacher-students";
    }

    @PostMapping("/createGrade")
    public String createGrade(@ModelAttribute("gradeDTO") GradeDTO gradeDTO, Model model) {
        gradeDTO = gradeService.fillGradeDTOWithAdditionalData(gradeDTO);
        model.addAttribute("gradeDTO", gradeDTO);
        return "grade-form";
    }

    @PostMapping("/saveGrade")
    public String saveGrade(@Valid @ModelAttribute("gradeDTO") GradeDTO gradeDTO, BindingResult result,Model model) {
        System.out.println(gradeDTO);
        if (result.hasErrors()) {
            gradeDTO = gradeService.fillGradeDTOWithAdditionalData(gradeDTO);
            System.out.println(gradeDTO);
            model.addAttribute("gradeDTO", gradeDTO);
            return "grade-form";
        }
        gradeService.saveGrade(gradeDTO);
        return "redirect:/teacher/getStudentsGrades?subjectId=" + gradeDTO.getSubjectId();
    }

    @GetMapping("/updateGrade/{gradeId}")
    public String updateGrade(@PathVariable("gradeId") long gradeId, Model model) {
        GradeDTO gradeDTO = gradeService.getGradeDTOByGradeId(gradeId);
        System.out.println(gradeDTO);
        model.addAttribute("gradeDTO", gradeDTO);
        return "grade-form";
    }

    @GetMapping("/deleteGrade/{gradeId}")
    public String deleteGrade(@PathVariable("gradeId") long gradeId) {
        Grade grade = gradeService.getGradeById(gradeId);
        gradeService.deleteGradeById(gradeId);
        return "redirect:/teacher/getStudentsGrades?subjectId=" + grade.getSubject().getId();
    }
}
