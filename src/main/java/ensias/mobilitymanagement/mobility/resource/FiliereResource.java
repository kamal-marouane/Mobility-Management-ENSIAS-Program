package ensias.mobilitymanagement.mobility.resource;

import ensias.mobilitymanagement.mobility.exception.FiliereNotFoundException;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiere")
public class FiliereResource {
    private final FiliereService filiereService;

    @Autowired
    public FiliereResource(FiliereService filiereService) {
        this.filiereService = filiereService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Filiere>> getAllFilieres() {
        List<Filiere> filieres = filiereService.findAllFilieres();
        return new ResponseEntity<>(filieres, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Filiere> getFiliereById(@PathVariable("id") Long id) {
        Filiere filiere = filiereService.findFiliereById(id);
        return new ResponseEntity<>(filiere, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Filiere> addFiliere(@RequestBody Filiere filiere) {
        Filiere newFiliere = filiereService.addFiliere(filiere);
        return new ResponseEntity<>(newFiliere, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Filiere> updateFiliere(@RequestBody Filiere filiere) {
        Filiere updatedFiliere = filiereService.updateFiliere(filiere);
        return new ResponseEntity<>(updatedFiliere, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFiliere(@PathVariable("id") Long id) {
        filiereService.deleteFiliere(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(FiliereNotFoundException.class)
    public ResponseEntity<?> handleFiliereNotFoundException(FiliereNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}

