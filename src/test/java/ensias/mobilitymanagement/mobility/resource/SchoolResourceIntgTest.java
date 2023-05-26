package ensias.mobilitymanagement.mobility.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.model.Student;
import ensias.mobilitymanagement.mobility.service.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class SchoolResourceIntgTest {

    @Mock
    private SchoolService schoolService;

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private School school;
    private List<School> schools;


    @BeforeEach
    void setUp() {
        school = new School();
        school.setSchoolId(11L);
        school.setSchoolName("SchoolFrance");
        school.setCity("Paris");
        school.setCountry("France");
        school.setNbr_dd(3);
        school.setNbr_ec(1);

        School school2 = new School();
        school2.setSchoolId(12L);
        school2.setSchoolName("SchoolAllemagne");
        school2.setCity("Hamburg");
        school2.setCountry("Allemagne");
        school2.setNbr_dd(4);
        school2.setNbr_ec(0);

        schools = Arrays.asList(school, school2);
    }

    @Test
    @DisplayName("POST /schools - Success")
    void testCreateSchool() throws Exception {

        School school = new School("Test School7", "Test Country", "Test City", 6, 2);

        String requestBody = "{\"schoolName\":\"Test School7\",\"country\":\"Test Country\",\"city\":\"Test City\",\"nbr_dd\":6,\"nbr_ec\":2}";

        mockMvc.perform(post("/schools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolId").exists())
                .andExpect(jsonPath("$.schoolName").value(school.getSchoolName()))
                .andExpect(jsonPath("$.country").value(school.getCountry()))
                .andExpect(jsonPath("$.city").value(school.getCity()))
                .andExpect(jsonPath("$.nbr_dd").value(school.getNbr_dd()))
                .andExpect(jsonPath("$.nbr_ec").value(school.getNbr_ec()));
    }

    @Test
    @DisplayName("DELETE /schools/{id} - Success")
    void testDeleteSchool() throws Exception {

        Long schoolId = 1L;

        mockMvc.perform(delete("/schools/{id}", schoolId))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

}