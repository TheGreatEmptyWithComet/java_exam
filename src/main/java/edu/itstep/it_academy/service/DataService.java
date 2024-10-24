package edu.itstep.it_academy.service;

import edu.itstep.it_academy.entity.Grade;
import edu.itstep.it_academy.entity.Subject;

import java.time.LocalDate;
import java.util.List;

public interface DataService {

    public List<LocalDate> getScheduleDates(List<Grade> grades);
}
