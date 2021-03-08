package org.tbot.rest;

import org.tbot.rest.entities.Action;
import org.tbot.rest.entities.AmbushWarning;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.FormedGroup;
import org.tbot.rest.repositories.ActionRepository;
import org.tbot.rest.repositories.AmbushWarningRepository;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.FormedGroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AmbushWarningCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private FormedGroupRepository formedGroupRepository;

    @Autowired
    private AmbushWarningRepository ambushWarningRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
        ambushWarningRepository.deleteAll();
        formedGroupRepository.deleteAll();
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

        final Action action = new Action(
                cityId,
                new Date(),
                new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)
        );

        final String actionId = actionRepository.save(action).getActionId();

        final FormedGroup formedGroup = new FormedGroup(
                cityId,
                actionId,
                52.3434545,
                52.3465345,
                "Точка начала",
                52.3434645,
                52.3465645,
                "Точка окончания",
                520
        );
        final String formedGroupId =
                formedGroupRepository.save(formedGroup).getFormedGroupId();

        final AmbushWarning ambushWarning = new AmbushWarning(
                formedGroupId,
                "Ленина 36",
                52.3474564,
                53.235343
        );

        mockMvc.perform(post("/ambush-warning")
                                .content(gson.toJson(ambushWarning)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("ambush-warning/"))
                );
    }
}
