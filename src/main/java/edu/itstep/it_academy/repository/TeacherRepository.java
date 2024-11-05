package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public Optional<Teacher> findByUsername(String username);
}
