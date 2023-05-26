package ensias.mobilitymanagement.mobility.repo;
import ensias.mobilitymanagement.mobility.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SchoolRepo extends JpaRepository<School, Long> {
    void deleteSchoolBySchoolId(Long schoolId);
    Optional<School> findSchoolBySchoolId(Long schoolId);
}
