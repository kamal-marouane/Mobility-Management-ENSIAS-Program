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

    /*

    @Test
    public void testFindSchoolById() {
        School school = new School("School 1", "Country 1", "City 1", 50, 120);

        when(schoolService.findSchoolById(1L)).thenReturn(school);

        ResponseEntity<School> response = schoolResource.findSchoolById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(school, response.getBody());
    }


    @Test
    public void findSchoolById() {
        Long id = 1L;
        School mockSchool = new School("Test School", "Test Country", "Test City", 50, 120);
        mockSchool.setSchoolId(id);

        Mockito.when(schoolService.findSchoolById(id)).thenReturn(mockSchool);

        ResponseEntity<School> response = schoolResource.findSchoolById(id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        School school = response.getBody();
        assertNotNull(school);
        assertEquals(mockSchool.getSchoolId(), school.getSchoolId());
        assertEquals(mockSchool.getSchoolName(), school.getSchoolName());
        assertEquals(mockSchool.getCountry(), school.getCountry());
        assertEquals(mockSchool.getCity(), school.getCity());
        assertEquals(mockSchool.getNbr_dd(), school.getNbr_dd());
        assertEquals(mockSchool.getNbr_ec(), school.getNbr_ec());
    }

    @Test
    void createSchool() {
        School schoolToCreate = new School("Test School", "Test Country", "Test City", 50, 120);
        School createdSchool = new School("Test School", "Test Country", "Test City", 50, 120);
        when(schoolService.addSchool(schoolToCreate)).thenReturn(createdSchool);

        ResponseEntity<School> response = schoolResource.createSchool(schoolToCreate);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdSchool, response.getBody());
    }


    @Test
    public void deleteSchool() {
        Long schoolId = 1L;
        School school = new School("SchoolName", "Country", "City", 50, 120);
        school.setSchoolId(schoolId);

        when(schoolService2.findSchoolById(schoolId)).thenReturn(school);
        doNothing().when(schoolService2).deleteSchool(schoolId);

        ResponseEntity<?> responseEntity = schoolResource2.deleteSchool(schoolId);

        verify(schoolService2, times(1)).findSchoolById(schoolId);
        verify(schoolService2, times(1)).deleteSchool(schoolId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

*/
}
