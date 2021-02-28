package org.example.rest;

import org.example.rest.entities.Action;
import org.example.rest.entities.City;
import org.example.rest.entities.CityGoal;
import org.example.rest.repositories.ActionRepository;
import org.example.rest.repositories.CityGoalRepository;
import org.example.rest.repositories.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActionGoalCreateTest extends EntityCreateTests {
    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityGoalRepository cityGoalRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
        cityGoalRepository.deleteAll();
        actionRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        final City city = new City(
                "Красноярск",
                "ФО Сибирский",
                1093771,
                92.52,
                56.00
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

        final Action action = new Action(
                cityId,
                new Date(),
                new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)
        );
        action.getCityGoals().add(cityGoal);
        cityGoal.getActions().add(action);
        actionRepository.save(action);


        mockMvc.perform(
                get("/action/" + action.getActionId() + "/cityGoals")
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(
                        content().string(
                                containsString(cityGoal.getCityGoalId()))
                );
    }
}
