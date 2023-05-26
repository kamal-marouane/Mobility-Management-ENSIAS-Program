package ensias.mobilitymanagement.mobility.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.service.StudentService;
import net.bytebuddy.matcher.ElementMatchers;
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

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

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
    @DisplayName("GET find/{id} - Success")
    void testFindStudentById() throws Exception {

        Student student = new Student(1L, "karim", "nouri", "1234");
        when(studentService.findStudentById(1L)).thenReturn(student);
        mockMvc.perform(get("/find/1", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId").value(1L))
                .andExpect(jsonPath("$.firstname").value("karim"))
                .andExpect(jsonPath("$.lastname").value("nouri"))
                .andExpect(jsonPath("$.codeApogee").value("1234"));

    }

    @Test
    void testUpdateStudent() throws Exception {
        Student updatedStudent = new Student(12L, "samira", "bensaid","239876");
        String updatedStudentJson = objectMapper.writeValueAsString(updatedStudent);

        when(studentService.updateStudent(any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedStudentJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId").value(12L))
                .andExpect(jsonPath("$.firstname").value("samira"))
                .andExpect(jsonPath("$.lastname").value("bensaid"))
                .andExpect(jsonPath("$.codeApogee").value("239876"));
    }

    @Test
    @DisplayName("DELETE /delete/{id} - Success")
    void testDeleteStudent() throws Exception {

        Long studentId = 1L;

        mockMvc.perform(delete("/delete/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }


}