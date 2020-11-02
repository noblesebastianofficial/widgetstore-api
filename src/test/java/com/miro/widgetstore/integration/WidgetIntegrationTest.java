package com.miro.widgetstore.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miro.widgetstore.testdata.DataSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Noble Sebastian
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
public class WidgetIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenValidInputForCreation_thenReturns200() throws Exception {
        boolean needValidInput = true;
        mockMvc.perform(post("/api/widgets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(DataSet.buildRequest(needValidInput, false))))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidInputForUpdate_thenReturns200() throws Exception {
        boolean needValidInput = true;
        mockMvc.perform(post("/api/widgets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(DataSet.buildRequest(needValidInput, true))))
                .andExpect(status().isOk());
    }

    @Test
    void whenInValidInput_thenReturns412() throws Exception {

        boolean needValidInput = false;
        mockMvc.perform(post("/api/widgets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(DataSet.buildRequest(needValidInput, false))))
                .andExpect(status().isPreconditionFailed());
    }



    @Test
    void whenXCordinatesPresentInForUpdate_thenReturns200() throws Exception {
        boolean needValidInput = true;
        mockMvc.perform(post("/api/widgets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(DataSet.buildRequest(needValidInput, true))))
                .andExpect(status().isOk());
    }

    @Test
    void deleteWidget_thenReturns200() throws Exception {
        boolean needValidInput = true;
        mockMvc.perform(post("/api/widgets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(DataSet.buildRequest(needValidInput, true))))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/api/widgets/1"))
                .andExpect(status().isOk());
    }

}
