package org.tbot.rest;

import com.google.gson.Gson;
import org.tbot.rest.entities.City;
import org.tbot.rest.repositories.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
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

        mockMvc.perform(post("/city")
                .content(new Gson().toJson(city)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("city/"))
                );
    }
}
