package org.tbot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.FunctionalRole;
import org.tbot.rest.entities.Roles;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.UserRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalRoleToUserCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

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
        final FunctionalRole functionalRole = new FunctionalRole(
                "Может просматривать контент",
                "Описание",
                "Условия получения"
        );
        final User user = new User(
                String.valueOf(faker.number().randomNumber(8, true)),
                Roles.CITIZEN.name(),
                cityId
        );

        functionalRole.getUsers().add(user);
        user.getFunctionalRoles().add(functionalRole);
        userRepository.save(user);

        mockMvc.perform(
                get("/user/" + user.getUserId() + "/functionalRoles")
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(
                        content().string(
                                containsString(functionalRole.getFunctionalRoleId()))
                );
    }
}
