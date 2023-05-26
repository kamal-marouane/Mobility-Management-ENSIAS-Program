package ensias.mobilitymanagement.mobility.service;

import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.repo.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepo studentRepositoryMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(studentRepositoryMock);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("12345", "Khalid", "Belaarbi", new Filiere(), "elarbi@gmail.com", "+1-123-456-7890", 12.5, 15.0, null);
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(student);

        Student result = studentService.addStudent(student);

        assertEquals(student, result);
        verify(studentRepositoryMock, times(1)).save(student);
    }

    @Test
    public void testFindAllStudents() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepositoryMock.findAll()).thenReturn(students);

        List<Student> result = studentService.findAllStudents();

        assertEquals(students, result);
        verify(studentRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student("123456", "samira", "bensaid", null, "samira.ben@gmail.com", "123456789", -1, -1, null);
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(student);

        Student result = studentService.updateStudent(student);

        assertEquals(student, result);
        verify(studentRepositoryMock, times(1)).save(student);
    }

    @Test
    public void testFindStudentById() {
        Student student = new Student("123456", "samira", "bensaid", null, "samira.ben@gmail.com", "123456789", -1, -1, null);
        Optional<Student> optionalStudent = Optional.of(student);
        when(studentRepositoryMock.findStudentByStudentId(anyLong())).thenReturn(optionalStudent);

        Student result = studentService.findStudentById(1L);

        assertEquals(student, result);
        verify(studentRepositoryMock, times(1)).findStudentByStudentId(1L);
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("123456", "samira", "bensaid", null, "samira.ben@gmail.com", "123456789", -1, -1, null);
        student.setStudentId(1L);

        when(studentRepositoryMock.findStudentByStudentId(student.getStudentId())).thenReturn(java.util.Optional.of(student));

        StudentService studentService = new StudentService(studentRepositoryMock);
        assertDoesNotThrow(() -> studentService.deleteStudent(student.getStudentId()));
        verify(studentRepositoryMock, times(1)).deleteStudentByStudentId(student.getStudentId());

    }
}