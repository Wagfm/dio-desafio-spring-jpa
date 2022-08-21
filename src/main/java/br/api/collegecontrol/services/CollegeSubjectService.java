package br.api.collegecontrol.services;

import br.api.collegecontrol.models.CollegeSubjectModel;
import br.api.collegecontrol.repositories.CollegeSubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CollegeSubjectService {
    private final CollegeSubjectRepository collegeSubjectRepository;

    @Transactional
    public CollegeSubjectModel save(CollegeSubjectModel collegeSubjectModel) {
        return this.collegeSubjectRepository.save(collegeSubjectModel);
    }

    public boolean existsBySubjectCode(String subjectCode) {
        return this.collegeSubjectRepository.existsBySubjectCode(subjectCode);
    }

    public List<CollegeSubjectModel> findAll() {
        return this.collegeSubjectRepository.findAll();
    }

    public Optional<CollegeSubjectModel> findById(UUID id) {
        return this.collegeSubjectRepository.findById(id);
    }

    public Optional<List<CollegeSubjectModel>> findBySubjectName(String subjectName) {
        return this.collegeSubjectRepository.findBySubjectName(subjectName);
    }

    @Transactional
    public void delete(CollegeSubjectModel collegeSubjectModel) {
        this.collegeSubjectRepository.delete(collegeSubjectModel);
    }

    public Optional<CollegeSubjectModel> findBySubjectCode(String subjectCode) {
        return this.collegeSubjectRepository.findBySubjectCode(subjectCode);
    }
}
