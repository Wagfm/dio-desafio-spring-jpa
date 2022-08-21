package br.api.collegecontrol.controllers;

import br.api.collegecontrol.dtos.CollegeSubjectDto;
import br.api.collegecontrol.models.CollegeSubjectModel;
import br.api.collegecontrol.services.CollegeSubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/college-manager")
public class CollegeSubjectController {
    private final CollegeSubjectService collegeSubjectService;

    @PostMapping
    public ResponseEntity<Object> saveCollegeSubject(@RequestBody @Valid CollegeSubjectDto collegeSubjectDto) {
        if (this.collegeSubjectService.existsBySubjectCode(collegeSubjectDto.getSubjectCode()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Subject Code is already in use!");
        var collegeSubjectModel = new CollegeSubjectModel();
        BeanUtils.copyProperties(collegeSubjectDto, collegeSubjectModel);
        collegeSubjectModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.collegeSubjectService.save(collegeSubjectModel));
    }

    @GetMapping
    public ResponseEntity<List<CollegeSubjectModel>> getAllCollegeSubjects() {
        return ResponseEntity.status(HttpStatus.OK).body(this.collegeSubjectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCollegeSubjectById(@PathVariable(value = "id") UUID id) {
        var collegeSubjectModelOptional = this.getById(id);
        if (collegeSubjectModelOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("College Subject not found.");

        return ResponseEntity.status(HttpStatus.OK).body(collegeSubjectModelOptional.get());
    }

    @GetMapping("/subjectCode={subjectCode}")
    public ResponseEntity<Object> getCollegeSubjectByCode(@PathVariable(value = "subjectCode") String subjectCode) {
        var collegeSubjectModelOptional = this.getByCode(subjectCode);
        if (collegeSubjectModelOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No College Subject found for this code.");
        return ResponseEntity.status(HttpStatus.OK).body(collegeSubjectModelOptional.get());
    }

    @GetMapping("/subjectName={subjectName}")
    public ResponseEntity<Object> getCollegeSubjectByName(@PathVariable(value = "subjectName") String subjectName) {
        var collegeSubjectModelOptional = this.getByName(subjectName);
        if (collegeSubjectModelOptional.isEmpty() || collegeSubjectModelOptional.get().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No College Subject found for this name.");
        return ResponseEntity.status(HttpStatus.OK).body(collegeSubjectModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCollegeSubjectById(@PathVariable(value = "id") UUID id) {
        var collegeSubjectModelOptional = this.getById(id);
        if (collegeSubjectModelOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("College Subject not found.");
        this.collegeSubjectService.delete(collegeSubjectModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("College Subject deleted successfully.");
    }

    @DeleteMapping("/subjectCode={subjectCode}")
    public ResponseEntity<Object> deleteCollegeSubjectByCode(@PathVariable(value = "subjectCode") String subjectCode) {
        var collegeSubjectModelOptional = this.getByCode(subjectCode);
        if (collegeSubjectModelOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("College Subject not found.");
        this.collegeSubjectService.delete(collegeSubjectModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("College Subject deleted successfully.");
    }

    @DeleteMapping("/subjectName={subjectName}")
    public ResponseEntity<Object> deleteCollegeSubjectByName(@PathVariable(value = "subjectName") String subjectName) {
        var collegeSubjectModelOptional = this.getByName(subjectName);
        if (collegeSubjectModelOptional.isEmpty() || collegeSubjectModelOptional.get().isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("College Subject not found.");
        collegeSubjectModelOptional.get().forEach(this.collegeSubjectService::delete);
        return ResponseEntity.status(HttpStatus.OK).body("College Subject deleted successfully.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCollegeSubject(@PathVariable(value = "id") UUID id,
                                                       @RequestBody @Valid CollegeSubjectDto collegeSubjectDto) {
        var collegeSubjectModelOptional = this.getById(id);
        if (collegeSubjectModelOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("College Subject not found.");
        var collegeSubjectModel = new CollegeSubjectModel();
        BeanUtils.copyProperties(collegeSubjectDto, collegeSubjectModel);
        collegeSubjectModel.setId(collegeSubjectModelOptional.get().getId());
        collegeSubjectModel.setRegistrationDate(collegeSubjectModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(collegeSubjectService.save(collegeSubjectModel));
    }

    private Optional<CollegeSubjectModel> getById(UUID subjectId){
        return this.collegeSubjectService.findById(subjectId);
    }

    private Optional<CollegeSubjectModel> getByCode(String subjectCode){
        return this.collegeSubjectService.findBySubjectCode(subjectCode);
    }

    private Optional<List<CollegeSubjectModel>> getByName(String subjectName){
        return this.collegeSubjectService.findBySubjectName(subjectName);
    }

}
