package br.api.collegecontrol.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "college_subject_tb")
public class CollegeSubjectModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private int subjectSemester;

    @Column(nullable = false, unique = true, length = 6)
    private String subjectCode;

    @Column(nullable = false, length = 70)
    private String subjectName;

    @Column(nullable = false)
    private int subjectCredits;

    @Column(nullable = false)
    private double finalGrade;

    @Column(nullable = false)
    private LocalDateTime registrationDate;
}
