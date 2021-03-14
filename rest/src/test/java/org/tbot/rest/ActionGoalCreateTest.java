package org.tbot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.Action;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.CityGoal;
import org.tbot.rest.repositories.ActionRepository;
import org.tbot.rest.repositories.CityRepository;

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
