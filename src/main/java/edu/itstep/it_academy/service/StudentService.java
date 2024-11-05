package edu.itstep.it_academy.service;

import edu.itstep.it_academy.dto.GradeOutDTO;
import edu.itstep.it_academy.dto.StudentOutDTO;
import edu.itstep.it_academy.dto.SubjectOutDTO;
import edu.itstep.it_academy.dto.TeacherStudentsDTO;
import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Student;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.entity.Teacher;
import edu.itstep.it_academy.exception.SubjectNotFoundException;
import edu.itstep.it_academy.mapper.*;
import edu.itstep.it_academy.repository.GradeRepository;
import edu.itstep.it_academy.repository.StudentRepository;
import edu.itstep.it_academy.repository.SubjectRepository;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    public TeacherStudentsDTO getStudentsGradesByDefaultSubject() {
        Teacher teacher = teacherService.getCurrentTeacher();
        var subjects = subjectRepository.findByTeacher(teacher);
        Subject defaultSubject;
        if (!subjects.isEmpty()) {
            defaultSubject = subjects.get(0);
        } else {
            defaultSubject = new Subject();
        }
        return getStudentsGradesBySubject(defaultSubject);
    }

    public TeacherStudentsDTO getStudentsGradesBySubject(Subject subject) {
        List<Grade> grades = new ArrayList<>();
        if (subject.getId() != null) {
            grades = gradeRepository.findGradesBySubjectOrderByDateDesc(subject);
        }
        return getTeacherStudentsDTO(grades, subject);
    }

    public TeacherStudentsDTO getStudentsGradesBySubjectAndDate(Subject subject, LocalDate date) {
        TeacherStudentsDTO studentTeacherDTO = new TeacherStudentsDTO();
        List<Grade> grades = gradeRepository.findGradesBySubjectAndDateOrderByDateDesc(subject, date);
        return getTeacherStudentsDTO(grades, subject);
    }

    public TeacherStudentsDTO getStudentsGradesBySubjectIdAndDate(Long id, LocalDate date) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(id));

        if (date == null) {
            return getStudentsGradesBySubject(subject);
        } else {
            return getStudentsGradesBySubjectAndDate(subject, date);
        }
    }

    private TeacherStudentsDTO getTeacherStudentsDTO(List<Grade> grades, Subject subject) {
        TeacherStudentsDTO teacherStudentsDTO = new TeacherStudentsDTO();

        Map<Student, List<Grade>> groupedGrades = grades.stream()
                .collect(Collectors.groupingBy(Grade::getStudent));

        Map<StudentOutDTO, List<GradeOutDTO>> studentsGrades = groupedGrades
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> studentMapper.toDTO(entry.getKey()),
                        entry -> entry.getValue().stream()
                                .map(gradeMapper::toOutDTO)
                                .collect(Collectors.toList())
                ));

        List<StudentOutDTO> allStudents = studentRepository
                .findAll(Sort.by("lastName"))
                .stream()
                .map(studentMapper::toDTO)
                .toList();

        allStudents.forEach((student) -> {
            studentsGrades.putIfAbsent(student, new ArrayList<GradeOutDTO>());
        });

        Map<StudentOutDTO, List<GradeOutDTO>> sortedStudentsGrades = studentsGrades.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(StudentOutDTO::getLastName))) // Sort by a key property
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Keep the key
                        Map.Entry::getValue, // Keep the value
                        (existing, replacement) -> existing,
                        LinkedHashMap::new // Preserve insertion order
                ));

        teacherStudentsDTO.setStudents(sortedStudentsGrades);

        teacherStudentsDTO.setSubjectId(subject.getId());

        Teacher teacher = teacherService.getCurrentTeacher();
        List<SubjectOutDTO> subjects = subjectRepository
                .findByTeacher(teacher)
                .stream().map(subjectMapper::toDTO)
                .toList();

        teacherStudentsDTO.setSubjects(subjects);

        teacherStudentsDTO.setTeacherOutDTO(teacherMapper.toDTO(teacher));

        return teacherStudentsDTO;
    }
}
