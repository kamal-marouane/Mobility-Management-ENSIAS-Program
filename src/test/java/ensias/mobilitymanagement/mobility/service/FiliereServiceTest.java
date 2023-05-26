package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.FiliereNotFoundException;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.repo.FiliereRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class FiliereServiceTest {

    @Mock
    private FiliereRepo filiereRepo;

    @InjectMocks
    private FiliereService filiereService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    @DisplayName("Add a filiere")
    void testAddFiliere() {
        Filiere filiere = Filiere.builder().filiereName("GL").build();

        Mockito.when(filiereRepo.save(Mockito.any(Filiere.class))).thenReturn(filiere);

        Filiere addedFiliere = filiereService.addFiliere(filiere);

        Assertions.assertEquals(filiere, addedFiliere);
    }

    @Test
    @DisplayName("Find all filieres")
    void testFindAllFilieres() {
        List<Filiere> filieres = new ArrayList<>();
        filieres.add(Filiere.builder().filiereId(1L).filiereName("GL").build());
        filieres.add(Filiere.builder().filiereId(2L).filiereName("SSE").build());

        Mockito.when(filiereRepo.findAll()).thenReturn(filieres);

        List<Filiere> foundFilieres = filiereService.findAllFilieres();

        Assertions.assertEquals(filieres, foundFilieres);
    }

    @Test
    @DisplayName("Find filiere by id")
    void testFindFiliereById() {
        Filiere filiere = Filiere.builder().filiereName("Data Science").build();

        Mockito.when(filiereRepo.findFiliereByFiliereId(Mockito.anyLong())).thenReturn(Optional.of(filiere));

        Filiere foundFiliere = filiereService.findFiliereById(filiere.getFiliereId());

        Assertions.assertEquals(filiere, foundFiliere);
    }

    @Test
    void testFindFiliereByIdNotFound() {
        Long id = 999L; // ID qui ne correspond à aucune filière existante

        assertThrows(FiliereNotFoundException.class, () -> filiereService.findFiliereById(id));
    }



    @Test
    @DisplayName("Update a filiere")
    void testUpdateFiliere() {
        Filiere filiere = Filiere.builder().filiereId(1L).filiereName("GL").build();

        Mockito.when(filiereRepo.save(Mockito.any(Filiere.class))).thenReturn(filiere);

        Filiere updatedFiliere = filiereService.updateFiliere(filiere);

        Assertions.assertEquals(filiere, updatedFiliere);
    }

    @Test
    @DisplayName("Delete a filiere")
    void testDeleteFiliere() {
        Filiere filiere = Filiere.builder().filiereId(1L).filiereName("GL").build();

        Mockito.when(filiereRepo.findFiliereByFiliereId(filiere.getFiliereId())).thenReturn(Optional.of(filiere));
        filiereService.deleteFiliere(filiere.getFiliereId());

        Mockito.verify(filiereRepo, Mockito.times(1)).deleteFiliereByFiliereId(filiere.getFiliereId());
    }

}