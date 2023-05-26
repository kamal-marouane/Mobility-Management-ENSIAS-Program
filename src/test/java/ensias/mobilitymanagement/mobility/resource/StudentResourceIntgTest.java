package ensias.mobilitymanagement.mobility.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class StudentResourceIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;
    private List<Student> students;


    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentId(4L);
        student.setFirstname("majda");
        student.setLastname("loali");
        student.setCodeApogee("786342");

        Student student2 = new Student();
        student2.setStudentId(5L);
        student2.setFirstname("khadija");
        student2.setLastname("Moradi");
        student2.setCodeApogee("7862837");

        students = Arrays.asList(student, student2);
    }

    @Test
    @DisplayName("GET /students/all - Success")
    void testGetAllStudents() throws Exception {
        List<Student> students = Arrays.asList(
                new Student(1L, "Said", "Rokai", "786544"),
                new Student(2L, "Moad", "Yatil", "896563")
        );
        when(studentService.findAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].studentId").value(students.get(0).getStudentId()))
                .andExpect(jsonPath("$[0].firstname").value(students.get(0).getFirstname()))
                .andExpect(jsonPath("$[0].lastname").value(students.get(0).getLastname()))
                .andExpect(jsonPath("$[0].codeApogee").value(students.get(0).getCodeApogee()))
                .andExpect(jsonPath("$[1].studentId").value(students.get(1).getStudentId()))
                .andExpect(jsonPath("$[1].firstname").value(students.get(1).getFirstname()))
                .andExpect(jsonPath("$[1].lastname").value(students.get(1).getLastname()))
                .andExpect(jsonPath("$[1].codeApogee").value(students.get(1).getCodeApogee()));
    }

    @Test
    @DisplayName("GET /students/find/{id} - Success")
    void testFindStudentById() throws Exception {
        Long studentId = 1L;
        Student student = new Student(studentId, "karim", "nouri", "1234");
        when(studentService.findStudentById(studentId)).thenReturn(student);
        mockMvc.perform(get("/students/find/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId").value(studentId))
                .andExpect(jsonPath("$.firstname").value("karim"))
                .andExpect(jsonPath("$.lastname").value("nouri"))
                .andExpect(jsonPath("$.codeApogee").value("1234"));
    }

    @Test
    @DisplayName("DELETE /students/delete/{id} - Success")
    void testDeleteStudent() throws Exception {

        Long studentId = 1L;

        mockMvc.perform(delete("/students/delete/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }


}