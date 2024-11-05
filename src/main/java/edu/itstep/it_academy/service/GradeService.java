package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.GradeDTO;
import edu.itstep.it_academy.dto.StudentGradesDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.exception.GradeNotFoundException;
import edu.itstep.it_academy.exception.SubjectNotFoundException;
import edu.itstep.it_academy.exception.UserNotFoundException;
import edu.itstep.it_academy.mapper.GradeMapper;
import edu.itstep.it_academy.mapper.StudentMapper;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;


    public GradeDTO fillGradeDTOWithAdditionalData(GradeDTO gradeDTO) {
        gradeDTO.setStudents(studentRepository.findAll());
        gradeDTO.setSubjects(subjectRepository.findAll());
        return gradeDTO;
    }

    public void saveGrade(GradeDTO gradeDTO) {
        Grade gradeFromForm = gradeMapper.toEntity(gradeDTO);
        gradeFromForm.setStudent(studentRepository.findById(gradeDTO.getStudentId()).orElse(null));
        gradeFromForm.setSubject(subjectRepository.findById(gradeDTO.getSubjectId()).orElse(null));

        if (gradeFromForm.getId() == null) {
            gradeRepository.save(gradeFromForm);
        } else {
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
        return gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException(id));
    }

    public GradeDTO getGradeDTOByGradeId(long id) {
        Grade grade = getGradeById(id);
        GradeDTO gradeDTO = gradeMapper.toDTO(grade);
        gradeDTO.setStudentId(grade.getStudent().getId());
        gradeDTO.setSubjectId(grade.getSubject().getId());
        gradeDTO = fillGradeDTOWithAdditionalData(gradeDTO);
        return gradeDTO;
    }

    @Transactional
    public void deleteGradeById(long id) {
        Grade grade = getGradeById(id);
        grade.getStudent().getGrades().remove(grade);
        gradeRepository.deleteById(id);
        System.out.println("Service delete");
    }

    public StudentGradesDTO getCurrentStudentGrades() {

        String studentUsername = customUserDetailsService.getCurrentUserUsername();
        Student student = studentRepository
                .findByUsername(studentUsername)
                .orElseThrow(() -> new UserNotFoundException(studentUsername));
        List<Grade> studentGrades = gradeRepository.findGradesByStudentOrderByDateDesc(student);
        Map<String, List<Grade>> studentGradesGrouped = studentGrades.stream()
                .collect(Collectors.groupingBy(grade -> grade.getSubject().getName()));

        List<Subject> subjects = subjectRepository.findAll();

        StudentGradesDTO studentGradesDTO = new StudentGradesDTO();
        studentGradesDTO.setGrades(studentGradesGrouped);
        studentGradesDTO.setSubjects(subjects);
        studentGradesDTO.setStudentOutDTO(studentMapper.toDTO(student));

        return studentGradesDTO;
    }

    public StudentGradesDTO getCurrentStudentGradesBySubjectId(long subjectId) {

        String studentUsername = customUserDetailsService.getCurrentUserUsername();
        Student student = studentRepository
                .findByUsername(studentUsername)
                .orElseThrow(() -> new UserNotFoundException(studentUsername));;
        Subject subject = subjectRepository
                .findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException(subjectId));

        List<Grade> studentGrades = gradeRepository.findGradesByStudentAndSubjectOrderByDateDesc(student, subject);
        Map<String, List<Grade>> studentGradesGrouped = studentGrades.stream()
                .collect(Collectors.groupingBy(grade -> grade.getSubject().getName()));

        List<Subject> subjects = subjectRepository.findAll();

        StudentGradesDTO studentGradesDTO = new StudentGradesDTO();
        studentGradesDTO.setGrades(studentGradesGrouped);
        studentGradesDTO.setSubjects(subjects);
        studentGradesDTO.setSubjectId(subjectId);
        studentGradesDTO.setStudentOutDTO(studentMapper.toDTO(student));

        return studentGradesDTO;
    }
}
