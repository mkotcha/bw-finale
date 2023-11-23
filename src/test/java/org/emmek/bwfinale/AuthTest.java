package org.emmek.bwfinale;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest extends ProjectApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void UnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isUnauthorized());
    }

//    @Test
//    public void GenerateAuthToken() throws Exception{
//        if (token != null) {
//            mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
//        }
//    }
}
