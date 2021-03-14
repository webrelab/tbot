package org.tbot.rest;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.tbot.rest.entities.City;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityCreateTest extends EntityCreateTests {

    @Test
    public void shouldCreateEntity() throws Exception {
        final City city = new City(
                faker.address().cityName(),
                faker.address().state(),
                faker.address().state(),
                faker.number().numberBetween(50000, 1500000),
                faker.number().randomDouble(6, -100, 100),
                faker.number().randomDouble(6, -100, 100)
        );

        mockMvc.perform(post("/city")
                .content(new Gson().toJson(city)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("city/"))
                );
    }
}
