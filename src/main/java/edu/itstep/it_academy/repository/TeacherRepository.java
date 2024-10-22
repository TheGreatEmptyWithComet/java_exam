package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;

import java.util.List;

public interface TeacherRepository {

    public List<Subject> getSubjects();

    public List<Grade> getStudentsGrades(Subject subject);
}
