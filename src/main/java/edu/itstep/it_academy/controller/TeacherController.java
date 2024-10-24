package edu.itstep.it_academy.controller;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.TeacherRepository;
import edu.itstep.it_academy.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DataService dataService;

    @GetMapping("/")
    public String getStudentsGrades(Model model) {
        Subject subject = teacherRepository.getSubjects().get(0);
        List<Grade> grades = teacherRepository.getStudentsGrades(subject);
        List<LocalDate> scheduleDates = dataService.getScheduleDates(grades);

        model.addAttribute("grades", grades);
        model.addAttribute("scheduleDates", scheduleDates);

        return "students-grades";
    }
}
