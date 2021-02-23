package org.example.rest;

import org.example.rest.entities.Action;
import org.example.rest.entities.ActionGoal;
import org.example.rest.entities.City;
import org.example.rest.entities.CityGoal;
import org.example.rest.repositories.ActionGoalRepository;
import org.example.rest.repositories.ActionRepository;
import org.example.rest.repositories.CityGoalRepository;
import org.example.rest.repositories.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActionGoalCreateTest extends EntityCreateTests {
    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityGoalRepository cityGoalRepository;

    @Autowired
    private ActionGoalRepository actionGoalRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
        actionGoalRepository.deleteAll();
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

        final String cityGoalId = cityGoalRepository.save(cityGoal).getCityGoalId();

        final Action action = new Action(
                cityId,
                new Date(),
                new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)
        );

        final String actionId = actionRepository.save(action).getActionId();

        final ActionGoal actionGoal = new ActionGoal(
                actionId,
                cityGoalId
        );

        mockMvc.perform(post("/action-goal")
                .content(gson.toJson(actionGoal)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("action-goal/"))
                );
    }
}
