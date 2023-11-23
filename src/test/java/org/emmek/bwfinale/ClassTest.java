package org.emmek.bwfinale;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassTest {

    @Value("${TEST_USER_JWT}")
    private String userToken;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testClienteGet() throws Exception {
        mockMvc.perform(get("/clienti/1").header("Authorization", userToken)).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.nomeContatto").value("bilgen")).andExpect(jsonPath("$.cognomeContatto").value("Rek"));
    }

    @Test
    public void testUtenteGet() throws Exception {

        mockMvc.perform(get("/users/2").header("Authorization", userToken)).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.nome").value("Tester")).andExpect(jsonPath("$.username").value("tester"));
    }

    @Test
    public void testFattura() throws Exception {
        mockMvc.perform(get("/fatture/1").header("Authorization", userToken)).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.importo").value("100.0"));
    }

    @Test
    public void testIndirizzo() throws Exception {
        mockMvc.perform(get("/indirizzi/1").header("Authorization", userToken)).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.nome").value("Tester")).andExpect(jsonPath("$.username").value("tester"));
    }
}
