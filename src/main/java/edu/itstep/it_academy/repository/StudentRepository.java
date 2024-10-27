package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.subject = :subject")
    public List<Student> findAllBySubject(@Param("subject")Subject subject);

    public Student findByUsername(String username);
}
