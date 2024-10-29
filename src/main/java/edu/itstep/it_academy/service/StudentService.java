package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.StudentTeacherDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;

    public StudentTeacherDTO getStudentsByDefaultSubject() {
        Subject defaultSubject = subjectRepository.findAll().get(0);
        return getStudentsBySubject(defaultSubject);
    }

    public StudentTeacherDTO getStudentsBySubject(Subject subject) {
        StudentTeacherDTO studentTeacherDTO = new StudentTeacherDTO();

        List<Student> students = studentRepository
                //.findAllBySubject(subject)
                .findAll()
                .stream()
                .map(student -> {
                    List<Grade> subjectGrades = student.getGrades().stream()
                            .filter(grade -> grade.getSubject().equals(subject)) // Оставляем только оценки по нужному предмету
                            .collect(Collectors.toList());
                    student.setGrades(subjectGrades);
                    return student;
                })
                .toList();

        studentTeacherDTO.setStudents(students);

        studentTeacherDTO.setSubjectId(subject.getId());

        List<Subject> subjects = subjectRepository.findAll();
        studentTeacherDTO.setSubjects(subjects);

        return studentTeacherDTO;
    }

    public StudentTeacherDTO getStudentsBySubjectId(long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        // TODO Change exception
        if (subject == null) {
            throw new RuntimeException("Subject with id " + id + " not fund");
        }

        return getStudentsBySubject(subject);
    }
}
