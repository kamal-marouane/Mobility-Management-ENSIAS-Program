package ensias.mobilitymanagement.mobility.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import ensias.mobilitymanagement.mobility.model.School;
import ensias.mobilitymanagement.mobility.service.SchoolService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    /*
        @Test
        @DisplayName("GET /schools/{id} - Found")
        void testFindSchoolByIdFound() throws Exception {
            Long id = 13L;
            School school = new School("School 1", "Country 1", "City 1", 10, 20);
            school.setSchoolId(id);

            when(schoolService.findSchoolById(id)).thenReturn(school);

            mockMvc.perform(get("/schools/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.schoolId").value(id))
                    .andExpect(jsonPath("$.schoolName").value("School 1"))
                    .andExpect(jsonPath("$.country").value("Country 1"))
                    .andExpect(jsonPath("$.city").value("City 1"))
                    .andExpect(jsonPath("$.nbr_dd").value(10))
                    .andExpect(jsonPath("$.nbr_ec").value(20));
        }

        @Test
        @DisplayName("GET /schools/{id} - Not Found")
        void testFindSchoolByIdNotFound() throws Exception {
            Long id = 1L;

            when(schoolService.findSchoolById(id)).thenReturn(null);

            mockMvc.perform(get("/schools/{id}", id))
                    .andExpect(status().isNotFound());
        }

     */
    @Test
    @DisplayName("POST /schools - Success")
    void testCreateSchool() throws Exception {

        School school = new School("Test School7", "Test Country", "Test City", 6, 2);

        String requestBody = "{\"schoolName\":\"Test School7\",\"country\":\"Test Country\",\"city\":\"Test City\",\"nbr_dd\":2,\"nbr_ec\":1}";

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

    /*
    @Test
    @DisplayName("DELETE /schools/{id} - Success")
    void testDeleteSchool() throws Exception {
        Long schoolId = 1L;

        mockMvc.perform(delete("/schools/{id}", schoolId))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
    */

}