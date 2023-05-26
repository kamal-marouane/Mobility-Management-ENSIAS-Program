package ensias.mobilitymanagement.mobility.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import ensias.mobilitymanagement.mobility.model.Filiere;
import ensias.mobilitymanagement.mobility.service.FiliereService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FiliereResourceIntgTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FiliereService filiereService;

    @Autowired
    private ObjectMapper objectMapper;

    private Filiere filiere;
    private List<Filiere> filieres;


    @BeforeEach
    void setUp() {
        filiere = Filiere.builder()
                .filiereId(1L)
                .filiereName("GL")
                .build();
        filieres = Arrays.asList(
                filiere,
                Filiere.builder()
                        .filiereId(2L)
                        .filiereName("SSI")
                        .build()
        );
    }

    @Test
    @DisplayName("GET /filiere/all - Success")
    void testGetAllFilieres() throws Exception {
        when(filiereService.findAllFilieres()).thenReturn(filieres);

        mockMvc.perform(get("/filiere/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].filiereId").value(1L))
                .andExpect(jsonPath("$[0].filiereName").value("GL"))
                .andExpect(jsonPath("$[1].filiereId").value(2L))
                .andExpect(jsonPath("$[1].filiereName").value("SSI"));
    }

    @Test
    @DisplayName("GET /filiere/find/{id} - Success")
    void testGetFiliereById() throws Exception {
        when(filiereService.findFiliereById(1L)).thenReturn(filiere);

        mockMvc.perform(get("/filiere/find/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.filiereId").value(1L))
                .andExpect(jsonPath("$.filiereName").value("GL"));
    }



    @Test
    @DisplayName("PUT /filiere/update - Success")
    void testUpdateFiliere() throws Exception {
        Filiere updatedFiliere = new Filiere(1L, "GL");
        String updatedFiliereJson = objectMapper.writeValueAsString(updatedFiliere);

        when(filiereService.updateFiliere(any(Filiere.class))).thenReturn(updatedFiliere);

        mockMvc.perform(put("/filiere/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedFiliereJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.filiereId").value(1L))
                .andExpect(jsonPath("$.filiereName").value("GL"));
    }

}