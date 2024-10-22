package edu.itstep.it_academy.repository;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TeacherRepositoryImpl implements TeacherRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Subject> getSubjects() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Subject ", Subject.class)
                .list();
    }


    @Override
    @Transactional
    public List<Grade> getStudentsGrades(Subject subject) {
        String hql = "FROM Grade g where subject = :subject";
        var result =  sessionFactory
                .getCurrentSession()
                .createQuery(hql, Grade.class)
                .setParameter("subject", subject)
                .list();
        return result;
    }
}
