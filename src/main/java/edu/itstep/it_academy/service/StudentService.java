package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.StudentsOutDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.GradeRepository;
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

    public StudentsOutDTO getStudentsByDefaultSubject(){
        Subject defaultSubject = subjectRepository.findAll().get(0);
        return getStudentsBySubject(defaultSubject);
    }

    public StudentsOutDTO getStudentsBySubject(Subject subject){
        StudentsOutDTO studentsOutDTO = new StudentsOutDTO();

        List<Student> students = studentRepository
                .findAllBySubject(subject)
                .stream()
                .map(student->{
                    List<Grade> subjectGrades = student.getGrades().stream()
                            .filter(grade -> grade.getSubject().equals(subject)) // Оставляем только оценки по нужному предмету
                            .collect(Collectors.toList());
                    student.setGrades(subjectGrades);
                    return student;
                })
                .toList();

        studentsOutDTO.setStudents(students);

        List<Subject> subjects = subjectRepository.findAll();
        studentsOutDTO.setSubjects(subjects);

        return studentsOutDTO;
    }


    String hql = "SELECT s FROM Student s JOIN s.grades g where g.subject.id = :id";
//        List<Student> students = sessionFactory
//                .getCurrentSession()
//                .createQuery(hql, Student.class)
//                .setParameter("id", subjectId)
//                .list();
//
//        for (Student student : students) {
//            List<Grade> selectedGrades = student.getGrades().stream().filter(g->g.getSubject().getId()==subjectId).collect(Collectors.toList());
//            student.setGrades(selectedGrades);
//        }

}
