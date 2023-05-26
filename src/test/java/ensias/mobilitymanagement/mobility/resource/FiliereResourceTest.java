package ensias.mobilitymanagement.mobility.resource;

import ensias.mobilitymanagement.mobility.exception.FiliereNotFoundException;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.service.FiliereService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FiliereResourceTest {

    @Mock
    FiliereService filiereService;

    FiliereResource filiereResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        filiereResource = new FiliereResource(filiereService);
    }

    @Test
    void getAllFilieres() {
        List<Filiere> filieres = new ArrayList<>();
        filieres.add(new Filiere(1L, "SSI"));
        filieres.add(new Filiere(2L, "GL"));
        when(filiereService.findAllFilieres()).thenReturn(filieres);

        ResponseEntity<List<Filiere>> response = filiereResource.getAllFilieres();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filieres, response.getBody());
    }

    @Test
    void getFiliereById() {
        Filiere filiere = new Filiere(1L, "GL");
        when(filiereService.findFiliereById(1L)).thenReturn(filiere);

        ResponseEntity<Filiere> response = filiereResource.getFiliereById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(filiere, response.getBody());
    }

    @Test
    void addFiliere() {
        Filiere filiereToAdd = new Filiere(1L, "GL");
        Filiere addedFiliere = new Filiere(1L, "GL");
        when(filiereService.addFiliere(filiereToAdd)).thenReturn(addedFiliere);

        ResponseEntity<Filiere> response = filiereResource.addFiliere(filiereToAdd);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addedFiliere, response.getBody());
    }

    @Test
    void updateFiliere() {
        Filiere filiereToUpdate = new Filiere(1L, "GL");
        Filiere updatedFiliere = new Filiere(1L, "BI&A");
        when(filiereService.updateFiliere(filiereToUpdate)).thenReturn(updatedFiliere);

        ResponseEntity<Filiere> response = filiereResource.updateFiliere(filiereToUpdate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFiliere, response.getBody());
    }

    @Test
    void deleteFiliere() {
        filiereResource.deleteFiliere(1L);
        verify(filiereService, times(1)).deleteFiliere(1L);
    }

    @Test
    public void testHandleFiliereNotFoundException() {

        FiliereNotFoundException exception = new FiliereNotFoundException("Filiere not found");
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;

        ResponseEntity<?> responseEntity = filiereResource.handleFiliereNotFoundException(exception);

        assertEquals(expectedStatus, responseEntity.getStatusCode());
        assertEquals("Filiere not found", responseEntity.getBody());
    }

}
