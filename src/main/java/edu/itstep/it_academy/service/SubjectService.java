package edu.itstep.it_academy.service;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubjectService {
    public List<Subject> getDistinctSubjectsFromGrades(List<Grade> grades) {
        return grades.stream()
                .map(Grade::getSubject)
                .distinct()
                .collect(Collectors.toList());
    }
}
