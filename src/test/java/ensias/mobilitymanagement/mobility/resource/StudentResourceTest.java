package ensias.mobilitymanagement.mobility.resource;

import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StudentResourceTest {

    @Mock
    private StudentService studentService;

    private StudentResource studentResource;

    @BeforeEach
    void setUp() {
        studentResource = new StudentResource(studentService);
    }

    @Test
    void getAllStudents() {
        List<Student> expectedStudents = Arrays.asList(new Student(), new Student());
        when(studentService.findAllStudents()).thenReturn(expectedStudents);

        ResponseEntity<List<Student>> response = studentResource.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudents, response.getBody());
        verify(studentService, times(1)).findAllStudents();
    }

    @Test
    void findStudentById() {
        Long studentId = 1L;
        Student expectedStudent = new Student();
        when(studentService.findStudentById(studentId)).thenReturn(expectedStudent);

        ResponseEntity<Student> response = studentResource.findStudentById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudent, response.getBody());
        verify(studentService, times(1)).findStudentById(studentId);
    }

    @Test
    void createStudent() {
        Student newStudent = new Student();
        Student expectedStudent = new Student();
        when(studentService.addStudent(newStudent)).thenReturn(expectedStudent);

        ResponseEntity<Student> response = studentResource.createStudent(newStudent);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedStudent, response.getBody());
        verify(studentService, times(1)).addStudent(newStudent);
    }

    @Test
    void updateStudent() {
        Student updatedStudent = new Student();
        Student expectedStudent = new Student();
        when(studentService.updateStudent(updatedStudent)).thenReturn(expectedStudent);

        ResponseEntity<Student> response = studentResource.updateStudent(updatedStudent);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStudent, response.getBody());
        verify(studentService, times(1)).updateStudent(updatedStudent);
    }

}
