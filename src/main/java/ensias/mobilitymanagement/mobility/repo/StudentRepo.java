package ensias.mobilitymanagement.mobility.repo;

import ensias.mobilitymanagement.mobility.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    void deleteStudentByStudentId(Long studentId);
    Optional<Student> findStudentByStudentId(Long student_id);
    List<Student> findStudentByFiliereFiliereName(String filiereName);
    Student findStudentByCodeApogee(String codeApogee);
}

