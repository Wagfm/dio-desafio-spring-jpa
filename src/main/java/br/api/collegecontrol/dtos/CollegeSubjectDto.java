package br.api.collegecontrol.dtos;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class CollegeSubjectDto {

    @Positive
    @Max(value = 18)
    private int subjectSemester;

    @NotBlank
    @Size(max = 6)
    private String subjectCode;

    @NotBlank
    @Size(max = 70)
    private String subjectName;

    @Positive
    @Max(value = 32)
    private int subjectCredits;

    @PositiveOrZero
    @Max(value = 100)
    private double finalGrade;
}
