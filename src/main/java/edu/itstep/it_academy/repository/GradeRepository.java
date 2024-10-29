package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    public void deleteById(long id);

//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId ORDER BY g.date")
//    List<Grade> findGradesByStudentIdSorted(@Param("studentId")Long studentId);

    List<Grade> findGradesByStudentOrderByDate(Student student);
    List<Grade> findGradesByStudentAndSubjectOrderByDate(Student student, Subject subject);
}
