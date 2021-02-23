package org.example.rest;

import org.example.rest.entities.*;
import org.example.rest.repositories.ActionRegistrationRepository;
import org.example.rest.repositories.ActionRepository;
import org.example.rest.repositories.CityRepository;
import org.example.rest.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActionRegistrationCreateTest extends EntityCreateTests{
    @Autowired
    private ActionRegistrationRepository actionRegistrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
        actionRegistrationRepository.deleteAll();
        actionRepository.deleteAll();
        userRepository.deleteAll();
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
        final User userData = new User(
                "6437658769",
                Roles.RESIDENT.getRoleName(),
                cityId
        );
        final String userId = userRepository.save(userData).getUserId();

        final Action action = new Action(
                cityId,
                new Date(),
                new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)
        );

        final String actionId = actionRepository.save(action).getActionId();

        final ActionRegistration actionRegistration = new ActionRegistration(
                actionId,
                userId,
                52.2453654,
                54.34524324,
                cityId
        );

        mockMvc.perform(post("/action-registration")
                .content(gson.toJson(actionRegistration)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("action-registration/"))
                );
    }
}
