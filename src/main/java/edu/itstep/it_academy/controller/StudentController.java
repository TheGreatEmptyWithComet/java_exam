package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.dto.StudentGradesDTO;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.service.CustomUserDetailsService;
import edu.itstep.it_academy.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeService gradeService;

    @GetMapping("/")
    public String getStudentsGrades(Model model) {
        //String studentUsername = customUserDetailsService.getCurrentUserUsername();
        StudentGradesDTO studentGradesDTO = gradeService.getCurrentStudentGrades();
        model.addAttribute("studentGradesDTO", studentGradesDTO);

        return "student-grades";
    }

    @PostMapping("/getStudentGradesBySubject")
    public String getStudentGradesBySubject(@RequestParam("subjectId") Long subjectId,Model model){
        StudentGradesDTO studentGradesDTO = gradeService.getCurrentStudentGradesBySubjectId(subjectId);
        model.addAttribute("studentGradesDTO", studentGradesDTO);
        return "student-grades";
    }
}
