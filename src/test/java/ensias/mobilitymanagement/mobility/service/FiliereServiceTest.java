package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.FiliereNotFoundException;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.repo.FiliereRepo;
import ensias.mobilitymanagement.mobility.repo.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
public class FiliereServiceTest {

    @Mock
    private FiliereRepo filiereRepo;

    @InjectMocks
    private FiliereService filiereService;

    @Mock
    private FiliereRepo filiereRepositoryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        filiereService = new FiliereService(filiereRepositoryMock);
    }

    @Test
    public void testAddFiliere() {
        Filiere filiere = new Filiere("GL");
        when(filiereRepositoryMock.save(any(Filiere.class))).thenReturn(filiere);

        Filiere result = filiereService.addFiliere(filiere);

        assertEquals(filiere, result);
        verify(filiereRepositoryMock, times(1)).save(filiere);
    }

    @Test
    public void testFindAllFilieres() {
        List<Filiere> filieres = Arrays.asList(new Filiere(), new Filiere());
        when(filiereRepositoryMock.findAll()).thenReturn(filieres);

        List<Filiere> result = filiereService.findAllFilieres();

        assertEquals(filieres, result);
        verify(filiereRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindFiliereById() {
        Filiere filiere = new Filiere("SSI");
        Optional<Filiere> optionalFiliere = Optional.of(filiere);
        when(filiereRepositoryMock.findFiliereByFiliereId(anyLong())).thenReturn(optionalFiliere);

        Filiere result = filiereService.findFiliereById(1L);

        assertEquals(filiere, result);
        verify(filiereRepositoryMock, times(1)).findFiliereByFiliereId(1L);
    }

    @Test
    void testFindFiliereByIdNotFound() {
        Long id = 999L; // ID qui ne correspond à aucune filière existante

        assertThrows(FiliereNotFoundException.class, () -> filiereService.findFiliereById(id));
    }

    @Test
    public void testUpdateFiliere() {
        Filiere filiere = new Filiere("BIA");
        when(filiereRepositoryMock.save(any(Filiere.class))).thenReturn(filiere);

        Filiere result = filiereService.updateFiliere(filiere);

        assertEquals(filiere, result);
        verify(filiereRepositoryMock, times(1)).save(filiere);
    }

    @Test
    public void testDeleteFiliere() {
        Filiere filiere = new Filiere("GL");
        filiere.setFiliereId(1L);

        when(filiereRepositoryMock.findFiliereByFiliereId(filiere.getFiliereId())).thenReturn(java.util.Optional.of(filiere));

        FiliereService filiereService = new FiliereService(filiereRepositoryMock);
        assertDoesNotThrow(() -> filiereService.deleteFiliere(filiere.getFiliereId()));
        verify(filiereRepositoryMock, times(1)).deleteFiliereByFiliereId(filiere.getFiliereId());

    }

}