package edu.itstep.it_academy.dto;


import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;

import java.util.List;


public class StudentsOutDTO {
    private Long subjectId;
    private List<Student> students;
    private List<Subject> subjects;


    public StudentsOutDTO(Long subjectId, List<Student> students, List<Subject> subjects) {
        this.subjectId = subjectId;
        this.students = students;
        this.subjects = subjects;
    }

    public StudentsOutDTO() {
    }


    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
