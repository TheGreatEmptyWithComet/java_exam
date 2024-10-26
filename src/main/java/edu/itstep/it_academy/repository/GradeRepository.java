package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
