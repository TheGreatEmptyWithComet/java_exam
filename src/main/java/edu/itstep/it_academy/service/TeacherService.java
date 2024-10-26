package edu.itstep.it_academy.service;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<LocalDate> getScheduleDates(List<Grade> grades) {

        return grades.stream().map(Grade::getDate).sorted().collect(Collectors.toList());

    }


//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Override
//    @Transactional
//    public List<Subject> getSubjects() {
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery("select distinct s from Subject s", Subject.class)
//                .list();
//    }
//
//
//    @Override
//    @Transactional
//    public List<Grade> getStudentsGrades(Subject subject) {
//        String hql = "FROM Grade g where subject = :subject";
//        var result = sessionFactory
//                .getCurrentSession()
//                .createQuery(hql, Grade.class)
//                .setParameter("subject", subject)
//                .list();
//        return result;
//    }
//
//    @Override
//    @Transactional
//    public List<Student> getStudentsBySubjectId(long subjectId) {
//        String hql = "SELECT s FROM Student s JOIN s.grades g where g.subject.id = :id";
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
//
//        return students;
//    }
//
//    @Override
//    @Transactional
//    public List<Student> getStudentsByDefaultSubject() {
//        Subject subject = getSubjects().get(0);
//        return getStudentsBySubjectId(subject.getId());
//    }
//
//    @Override
//    @Transactional
//    public Subject getSubjectById(long id) {
//        String hql = "from Subject s where s.id = :id";
//        Subject subject = sessionFactory
//                .getCurrentSession()
//                .createQuery(hql, Subject.class)
//                .setParameter("id", id)
//                .list()
//                .get(0);
//        return subject;
//    }
}
