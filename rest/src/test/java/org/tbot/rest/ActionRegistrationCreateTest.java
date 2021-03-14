package org.tbot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.*;
import org.tbot.rest.repositories.ActionRepository;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.UserRepository;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ActionRegistrationCreateTest extends EntityCreateTests{

    @Autowired
    private UserRepository userRepository;

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
        final User user = new User(
                String.valueOf(faker.number().randomNumber(8, true)),
                Roles.RESIDENT.name(),
                cityId
        );
        final String userId = userRepository.save(user).getUserId();

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
