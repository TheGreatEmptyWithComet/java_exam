package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    public void deleteById(long id);

//    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId ORDER BY g.date")
//    List<Grade> findGradesByStudentIdSorted(@Param("studentId")Long studentId);

    List<Grade> findGradesByStudentOrderByDateDesc(Student student);
    List<Grade> findGradesByStudentAndSubjectOrderByDateDesc(Student student, Subject subject);

    List<Grade> findGradesBySubjectOrderByDateDesc(Subject subject);
    List<Grade> findGradesBySubjectAndDateOrderByDateDesc(Subject subject, LocalDate date);
}
