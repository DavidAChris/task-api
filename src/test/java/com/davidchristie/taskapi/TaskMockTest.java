package com.davidchristie.taskapi;


import com.davidchristie.taskapi.task.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskMockTest {
    @Autowired
    private MockMvc mockMvc;

    private final PostTask newTask = new PostTask("Test Title", "Test Description");
    private final String newTaskJson = asJsonString(newTask);

    @Test
    void shouldReturnEmptyList() throws Exception {
        ResultActions request = this.mockMvc.perform(get("/tasks"));
        request.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(new ArrayList<Task>())));
    }

    @Test
    void shouldCreateATask() throws Exception {
        this.mockMvc.perform(post("/tasks")
                        .content(newTaskJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOneTaskById() throws Exception {
        this.mockMvc.perform(post("/tasks")
                        .content(newTaskJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/tasks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(1)));
    }

    @Test
    void shouldReturnAllTasks() throws Exception {
        for (int i = 0; i < 10; i++) {
            this.mockMvc.perform(post("/tasks")
                            .content(newTaskJson)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        this.mockMvc.perform(get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    void shouldCompleteTask() throws Exception {
        this.mockMvc.perform(post("/tasks")
                        .content(newTaskJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        this.mockMvc.perform(put("/tasks/1")
                        .param("completed", "true"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/tasks/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("completed", is(true)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
