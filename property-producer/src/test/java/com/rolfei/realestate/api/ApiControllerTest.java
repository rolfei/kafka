package com.rolfei.realestate.api;

import com.rolfei.realestate.HouseApplication;
import com.rolfei.realestate.api.ApiController;
import com.rolfei.realestate.kschema.HouseSchema;
import com.rolfei.realestate.producer.HouseAvroProducer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(properties = "spring.cloud.azure.appconfiguration.enabled=false")
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiControllerTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseAvroProducer houseAvroProducer;


    @MockBean
    private HouseSchema houseSchema;

    @Test
    void getHouses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/getHouses/1")).andExpect(status().isOk());

    }

    @Test
    void createHouse() throws Exception {
        String json ="{\"id\": \"1\", \"address\": \"myaddress\", \"country\": \"UK\",\"purpose\": \"ladeside\",\"price\": \"60000\" }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/createHouse").contentType(MediaType.APPLICATION_JSON).content(json.getBytes(StandardCharsets.UTF_8))).andExpect(status().isOk());
    }
}