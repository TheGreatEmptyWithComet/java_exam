package edu.itstep.it_academy.dto;


public class SubjectFormDTO {
    private Long subjectId;

    public SubjectFormDTO(Long subjectId) {
        this.subjectId = subjectId;
    }

    public SubjectFormDTO() {
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
