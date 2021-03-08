package org.tbot.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.*;
import org.tbot.rest.repositories.ActionRepository;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.FormedGroupRepository;
import org.tbot.rest.repositories.UserRepository;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FormedGroupUserCreateTest extends EntityCreateTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormedGroupRepository formedGroupRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ActionRepository actionRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
        userRepository.deleteAll();
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
        final User user = new User(
                "847457969",
                Roles.CITIZEN.name(),
                cityId
        );

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

        formedGroup.getUsers().add(user);
        final String formedGroupId =
                formedGroupRepository.save(formedGroup).getFormedGroupId();

        mockMvc.perform(
                get("/formed-group/" + formedGroupId + "/users")
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(
                        content().string(
                                containsString(user.getUserId()))
                );
    }
}
