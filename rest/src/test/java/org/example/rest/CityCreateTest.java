package org.example.rest;

import com.google.gson.Gson;
import org.example.rest.entities.City;
import org.example.rest.repositories.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class CityCreateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    public void clearAll() {
        cityRepository.deleteAll();
    }

    @AfterEach
    public void clearAllAfter() {
        cityRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        final City city = new City(
                "Новосибирск",
                "ФО Сибирский",
                1625631,
                82.55,
                55.01
        );
        final Map<String, String> dataMap = new HashMap<>();
        dataMap.put("cityName", "Новосибирск");
        dataMap.put("federalDistrict", "ФО Сибирский");
        dataMap.put("population", "1625631");
        dataMap.put("cityLon", "82.55");
        dataMap.put("cityLat", "55.01");

        mockMvc.perform(post("/city")
                .content(new Gson().toJson(dataMap)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("city/"))
                );
    }
}
