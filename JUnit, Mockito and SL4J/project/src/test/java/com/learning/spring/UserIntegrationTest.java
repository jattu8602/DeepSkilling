package com.learning.spring;

import com.learning.spring.entity.User;
import com.learning.spring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFullFlow() throws Exception {
        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"IntegrationTest\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("IntegrationTest"))
            .andExpect(jsonPath("$.id").isNumber());

        assertThat(userRepository.count()).isPositive();
    }

    @Test
    void testGetUserNotFound() throws Exception {
        mockMvc.perform(get("/users/999"))
            .andExpect(status().isNotFound());
    }
}
