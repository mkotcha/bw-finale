package org.emmek.bwfinale.tester;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ClassTester extends Tester {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testClienteGet() throws Exception{
mockMvc.perform(get("/clienti/1").header("Authorization","Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzAwNjQwODYwLCJleHAiOjE3MDEyNDU2NjB9.uHGixoMWKBrJ66yB5YqzG7TGL_PSp7A0IAdW3HT5HlS0fg9EcqP7zDEHdvtVSjP-")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.nomeContatto").value("bilgen")).andExpect(jsonPath("$.cognomeContatto").value("Rek"));
    }
    @Test
    public void testUtenteGet() throws Exception{
        mockMvc.perform(get("/users/2").header("Authorization","Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzAwNjQwODYwLCJleHAiOjE3MDEyNDU2NjB9.uHGixoMWKBrJ66yB5YqzG7TGL_PSp7A0IAdW3HT5HlS0fg9EcqP7zDEHdvtVSjP-")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.nome").value("Tester")).andExpect(jsonPath("$.username").value("tester"));
    }
    @Test
    public void testFattura() throws Exception{
        mockMvc.perform(get("/fatture/1").header("Authorization","Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzAwNjQwODYwLCJleHAiOjE3MDEyNDU2NjB9.uHGixoMWKBrJ66yB5YqzG7TGL_PSp7A0IAdW3HT5HlS0fg9EcqP7zDEHdvtVSjP-")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.importo").value("100.0"));
    }
    @Test
    public void testIndirizzo() throws Exception{
        mockMvc.perform(get("/indirizzi/1").header("Authorization","Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzAwNjQwODYwLCJleHAiOjE3MDEyNDU2NjB9.uHGixoMWKBrJ66yB5YqzG7TGL_PSp7A0IAdW3HT5HlS0fg9EcqP7zDEHdvtVSjP-")).andExpect(status().isOk()).andExpect(content().contentType("application/json")).andExpect(jsonPath("$.nome").value("Tester")).andExpect(jsonPath("$.username").value("tester"));
    }
}
