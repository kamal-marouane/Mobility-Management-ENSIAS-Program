package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.exception.SchoolNotFoundException;
import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.repo.SchoolRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSchool() {
        School school = new School("Insa Lyon", "France", "Paris", 10, 5);
        when(schoolRepo.save(any(School.class))).thenReturn(school);

        School result = schoolService.addSchool(school);

        verify(schoolRepo, times(1)).save(school);
        assertEquals(school, result);
    }

    @Test
    public void testFindAllSchools() {
        List<School> schools = new ArrayList<>();
        schools.add(new School("TSP", "France", "Paris", 1, 1));
        schools.add(new School("MIT", "Etat-Unis", "Massachusetts", 2, 2));
        when(schoolRepo.findAll()).thenReturn(schools);

        List<School> result = schoolService.findAllSchools();

        verify(schoolRepo, times(1)).findAll();
        assertEquals(schools, result);
    }

    @Test
    public void testUpdateSchool() {
        School school = new School("Insa Lyon", "France", "Paris", 10, 5);
        when(schoolRepo.save(any(School.class))).thenReturn(school);

        School result = schoolService.updateSchool(school);

        verify(schoolRepo, times(1)).save(school);
        assertEquals(school, result);
    }

    @Test
    public void testFindSchoolByIdSuccess() {
        Long id = 1L;
        School school = new School("Insa Lyon", "France", "Paris", 10, 5);
        when(schoolRepo.findSchoolBySchoolId(id)).thenReturn(Optional.of(school));

        School result = schoolService.findSchoolById(id);

        verify(schoolRepo, times(1)).findSchoolBySchoolId(id);
        assertEquals(school, result);
    }

    @Test
    public void testFindSchoolByIdNotFound() {
        Long id = 1L;
        when(schoolRepo.findSchoolBySchoolId(id)).thenReturn(Optional.empty());

        assertThrows(SchoolNotFoundException.class, () -> schoolService.findSchoolById(id));
    }

    @Test
    public void testDeleteSchool() {
        Long id = 1L;

        schoolService.deleteSchool(id);

        verify(schoolRepo, times(1)).deleteSchoolBySchoolId(id);
    }

}