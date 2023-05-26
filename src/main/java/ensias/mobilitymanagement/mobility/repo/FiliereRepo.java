package ensias.mobilitymanagement.mobility.repo;

import ensias.mobilitymanagement.mobility.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere,Long> {
    void deleteFiliereByFiliereId(Long filiereId);
    Optional<Filiere> findFiliereByFiliereId(Long filiereId);
    Filiere findByFiliereName(String filiereName);
}
