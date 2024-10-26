package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public List<Subject> getSubjects();

    public List<Grade> getStudentsGrades(Subject subject);

    public List<Student> getStudentsBySubjectId(long subjectId);
    public List<Student> getStudentsByDefaultSubject();
    public Subject getSubjectById(long id);
}
