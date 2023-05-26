package ensias.mobilitymanagement.mobility.resource;

import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class SchoolResourceTest {
    @Mock
    private SchoolService schoolService;

    @InjectMocks
    private SchoolResource schoolResource;

    private final SchoolService schoolService2 = mock(SchoolService.class);
    private final SchoolResource schoolResource2 = new SchoolResource(schoolService);

    @Test
    public void GetAllSchools() {
        List<School> expectedSchools = new ArrayList<School>();
        expectedSchools.add(new School("School1", "Country1", "City1", 10, 20));
        expectedSchools.add(new School("School2", "Country2", "City2", 5, 15));

        SchoolService schoolServiceMock = mock(SchoolService.class);
        when(schoolServiceMock.findAllSchools()).thenReturn(expectedSchools);

        SchoolResource schoolController = new SchoolResource(schoolServiceMock);
        ResponseEntity<List<School>> response = schoolController.getAllSchools();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<School> actualSchools = response.getBody();
        assertEquals(expectedSchools, actualSchools);
    }

}
