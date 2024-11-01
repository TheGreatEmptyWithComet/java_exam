package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public List<Subject> findByTeacher(Teacher teacher);
}
