package edu.itstep.it_academy.service;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Subject;
import edu.itstep.it_academy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<LocalDate> getScheduleDates(List<Grade> grades) {

        return grades.stream().map(Grade::getDate).sorted().collect(Collectors.toList());

    }

}
