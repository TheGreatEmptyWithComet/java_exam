package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.StudentTeacherDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.entity.Teacher;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import edu.itstep.it_academy.repository.TeacherRepository;
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
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherService teacherService;

    // TODO add error handling
    public StudentTeacherDTO getStudentsByDefaultSubject() {
        Teacher teacher = teacherService.getCurrentTeacher();
        Subject defaultSubject = subjectRepository.findByTeacher(teacher).get(0);
        return getStudentsBySubject(defaultSubject);
    }

    public StudentTeacherDTO getStudentsBySubject(Subject subject) {
        StudentTeacherDTO studentTeacherDTO = new StudentTeacherDTO();

        List<Student> students = studentRepository
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

        Teacher teacher = teacherService.getCurrentTeacher();
        List<Subject> subjects = subjectRepository.findByTeacher(teacher);
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
