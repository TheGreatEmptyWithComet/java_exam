package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.StudentGradesDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final SubjectService subjectService;
    private final CustomUserDetailsService customUserDetailsService;
    @PersistenceContext
    private EntityManager entityManager;

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    public GradeService(StudentRepository studentRepository, SubjectRepository subjectRepository, GradeRepository gradeRepository, SubjectService subjectService, CustomUserDetailsService customUserDetailsService) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.gradeRepository = gradeRepository;
        this.subjectService = subjectService;
        this.customUserDetailsService = customUserDetailsService;
    }

    public GradeDTO fillGradeDTO(GradeDTO gradeDTO) {
        gradeDTO.setStudents(studentRepository.findAll());
        gradeDTO.setSubjects(subjectRepository.findAll());
        return gradeDTO;
    }

    public void saveGrade(GradeDTO gradeDTO) {
        Grade gradeFromForm = gradeDTO.getGrade();
        gradeFromForm.setStudent(studentRepository.findById(gradeDTO.getStudentId()).orElse(null));
        gradeFromForm.setSubject(subjectRepository.findById(gradeDTO.getSubjectId()).orElse(null));

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

    @Transactional
    public void deleteGradeById(long id){
        Grade grade = getGradeById(id);
        grade.getStudent().getGrades().remove(grade);
        gradeRepository.deleteById(id);
        System.out.println("Service delete");
    }

    public StudentGradesDTO getCurrentStudentGrades(){

        String studentUsername = customUserDetailsService.getCurrentUserUsername();
        Student student = studentRepository.findByUsername(studentUsername).get();
        List<Grade> studentGrades = gradeRepository.findGradesByStudentOrderByDate(student);
        Map<String,List<Grade>> studentGradesGrouped = studentGrades.stream()
                .collect(Collectors.groupingBy(grade -> grade.getSubject().getName()));

        //List<Subject> subjects = subjectService.getDistinctSubjectsFromGrades(studentGrades);
        List<Subject> subjects = subjectRepository.findAll();

        StudentGradesDTO studentGradesDTO = new StudentGradesDTO();
        studentGradesDTO.setGrades(studentGradesGrouped);
        studentGradesDTO.setSubjects(subjects);
        studentGradesDTO.setStudent(student);

        return studentGradesDTO;
    }

    public StudentGradesDTO getCurrentStudentGradesBySubjectId(long subjectId){

        String studentUsername = customUserDetailsService.getCurrentUserUsername();
        Student student = studentRepository.findByUsername(studentUsername).get();
        Subject subject = subjectRepository.findById(subjectId).get();

        List<Grade> studentGrades = gradeRepository.findGradesByStudentAndSubjectOrderByDate(student,subject);
        Map<String,List<Grade>> studentGradesGrouped = studentGrades.stream()
                .collect(Collectors.groupingBy(grade -> grade.getSubject().getName()));

        List<Subject> subjects = subjectRepository.findAll();

        StudentGradesDTO studentGradesDTO = new StudentGradesDTO();
        studentGradesDTO.setGrades(studentGradesGrouped);
        studentGradesDTO.setSubjects(subjects);
        studentGradesDTO.setSubjectId(subjectId);
        studentGradesDTO.setStudent(student);

        return studentGradesDTO;
    }
}
