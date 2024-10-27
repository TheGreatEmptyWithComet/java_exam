package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @PersistenceContext
    private EntityManager entityManager;

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    public GradeService(StudentRepository studentRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
    }

    public GradeDTO fillGradeDTO(GradeDTO gradeDTO) {
        gradeDTO.setStudents(studentRepository.findAll());
        gradeDTO.setSubjects(subjectRepository.findAll());
        return gradeDTO;
    }

    public void saveGrade(GradeDTO gradeDTO) {
        Grade gradeFromForm = gradeDTO.getGrade();
        gradeFromForm.setStudent(studentRepository.findById(gradeDTO.getStudentId()).get());
        gradeFromForm.setSubject(subjectRepository.findById(gradeDTO.getSubjectId()).get());

        if (gradeFromForm.getId() == null) {
            gradeRepository.save(gradeFromForm);
        }else{
            Grade gradeFromDB = gradeRepository.findById(gradeFromForm.getId()).get();
            gradeFromDB.setSubject(gradeFromForm.getSubject());
            gradeFromDB.setStudent(gradeFromForm.getStudent());
            gradeFromDB.setComment(gradeFromForm.getComment());
            gradeFromDB.setDate(gradeFromForm.getDate());
            gradeFromDB.setGrade(gradeFromForm.getGrade());
            gradeRepository.save(gradeFromDB);
        }
    }

    public Grade getGradeById(long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new RuntimeException("No grade with id " + id + " found"));
    }

    public GradeDTO getGradeDTOByGradeId(long id) {
        GradeDTO gradeDTO = new GradeDTO();
        Grade grade = getGradeById(id);
        gradeDTO.setGrade(grade);
        gradeDTO.setStudentId(grade.getStudent().getId());
        gradeDTO.setSubjectId(grade.getSubject().getId());
        gradeDTO = fillGradeDTO(gradeDTO);
        return gradeDTO;
    }
}
