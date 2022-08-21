package br.api.collegecontrol.repositories;

import br.api.collegecontrol.models.CollegeSubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CollegeSubjectRepository extends JpaRepository<CollegeSubjectModel, UUID> {
    boolean existsBySubjectCode(String subjectCode);

    Optional<List<CollegeSubjectModel>> findBySubjectName(String subjectName);

    Optional<CollegeSubjectModel> findBySubjectCode(String subjectCode);
}
