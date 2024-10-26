package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import org.springframework.stereotype.Service;


@Service
public class GradeService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    public GradeService(StudentRepository studentRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
    }

    public GradeDTO getGradeDTO(GradeDTO gradeDTO){
        gradeDTO.setStudents(studentRepository.findAll());
        gradeDTO.setSubjects(subjectRepository.findAll());
        return gradeDTO;
    }

    public void saveGrade(GradeDTO gradeDTO){
        Grade grade = gradeDTO.getGrade();
        grade.setStudent(studentRepository.findById(gradeDTO.getStudentId()).get());
        grade.setSubject(subjectRepository.findById(gradeDTO.getSubjectId()).get());
        gradeRepository.saveAndFlush(gradeDTO.getGrade());
    }
}
