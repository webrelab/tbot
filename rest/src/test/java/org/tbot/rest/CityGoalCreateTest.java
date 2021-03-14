package org.tbot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.CityGoal;
import org.tbot.rest.repositories.CityRepository;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityGoalCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

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
        final String cityId = cityRepository.save(city).getCityId();

        final CityGoal cityGoal = new CityGoal(
                cityId,
                "Новая цель",
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                        "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. " +
                "Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. Длинное описание новой цели. ",
                new Date()
        );

        mockMvc.perform(post("/city-goal")
                .content(gson.toJson(cityGoal)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("city-goal/"))
                );
    }
}
