package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.FiliereNotFoundException;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.repo.FiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereService {
    private final FiliereRepo filiereRepo;

    @Autowired
    public FiliereService(FiliereRepo filiereRepo) {
        this.filiereRepo = filiereRepo;
    }

    public FiliereRepo getFiliereRepo() {
        return filiereRepo;
    }

    public Filiere addFiliere(Filiere filiere) {
        return filiereRepo.save(filiere);
    }

    public List<Filiere> findAllFilieres() {
        return filiereRepo.findAll();
    }

    public Filiere updateFiliere(Filiere filiere) {
        return filiereRepo.save(filiere);
    }

    public Filiere findFiliereById(Long id) {
        return filiereRepo.findFiliereByFiliereId(id)
                .orElseThrow(() -> new FiliereNotFoundException("filiere by id " + id + " was not found"));
    }

    public void deleteFiliere(Long id){
        filiereRepo.deleteFiliereByFiliereId(id);
    }
}
