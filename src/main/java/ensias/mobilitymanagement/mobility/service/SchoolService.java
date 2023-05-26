package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.SchoolNotFoundException;
import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.repo.SchoolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepo schoolRepository;

    @Autowired
    public SchoolService(SchoolRepo schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    public School updateSchool(School school) {
        return schoolRepository.save(school);
    }

    public School findSchoolById(Long id) {
        return schoolRepository.findSchoolBySchoolId(id)
                .orElseThrow(() -> new SchoolNotFoundException("School by id " + id + " was not found"));
    }

    public void deleteSchool(Long id) {
        schoolRepository.deleteSchoolBySchoolId(id);
    }
}