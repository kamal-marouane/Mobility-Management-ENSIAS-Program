package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.SchoolNotFoundException;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.repo.SchoolRepo;
import ensias.mobilitymanagement.mobility.repo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SchoolServiceTest {

    @Mock
    private SchoolRepo schoolRepo;

    @InjectMocks
    private SchoolService schoolService;

    @Mock
    private SchoolRepo schoolRepositoryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        schoolService = new SchoolService(schoolRepositoryMock);
    }

    @Test
    public void testAddSchool() {
        School school = new School("SchoolTest", "CountryTest", "CityTest", 6, 0);
        when(schoolRepositoryMock.save(any(School.class))).thenReturn(school);

        School result = schoolService.addSchool(school);

        assertEquals(school, result);
        verify(schoolRepositoryMock, times(1)).save(school);
    }


    @Test
    public void testFindAllSchools() {
        List<School> schools = Arrays.asList(new School(), new School());
        when(schoolRepositoryMock.findAll()).thenReturn(schools);

        List<School> result = schoolService.findAllSchools();

        assertEquals(schools, result);
        verify(schoolRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testUpdateSchool() {
        School school = new School("Insa Lyon", "France", "Paris", 10, 5);
        when(schoolRepositoryMock.save(any(School.class))).thenReturn(school);

        School result = schoolService.updateSchool(school);

        assertEquals(school, result);
        verify(schoolRepositoryMock, times(1)).save(school);
    }

    @Test
    public void testFindSchoolByIdSuccess() {
        School school = new School("Insa Lyon", "France", "Paris", 10, 5);
        Optional<School> optionalSchool = Optional.of(school);
        when(schoolRepositoryMock.findSchoolBySchoolId(anyLong())).thenReturn(optionalSchool);
        School result = schoolService.findSchoolById(1L);
        assertEquals(school, result);
        verify(schoolRepositoryMock, times(1)).findSchoolBySchoolId(1L);
    }

    @Test
    public void testFindSchoolByIdNotFound() {
        Long id = 1L;
        when(schoolRepo.findSchoolBySchoolId(id)).thenReturn(Optional.empty());

        assertThrows(SchoolNotFoundException.class, () -> schoolService.findSchoolById(id));
    }

    @Test
    public void testDeleteSchool() {
        School school = new School("TestSchool", "ContryTest", "CityTest", 2, 2);
        school.setSchoolId(1L);

        when(schoolRepositoryMock.findSchoolBySchoolId(school.getSchoolId())).thenReturn(java.util.Optional.of(school));

        SchoolService schoolService = new SchoolService(schoolRepositoryMock);
        assertDoesNotThrow(() -> schoolService.deleteSchool(school.getSchoolId()));
        verify(schoolRepositoryMock, times(1)).deleteSchoolBySchoolId(school.getSchoolId());

    }
}