package org.tbot.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.*;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.FunctionalRoleRepository;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VotingCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FunctionalRoleRepository functionalRoleRepository;

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
        final String functionalRoleId =
                functionalRoleRepository.save(functionalRole).getFunctionalRoleId();
        final Voting voting = new Voting(
                cityId,
                "h9h9v8h00fh034f0h",
                VotingEntityTypes.CITY_GOAL.name(),
                Roles.RESIDENT.name(),
                functionalRoleId,
                new Date(System.currentTimeMillis() - 5 * 24 * 60 * 60 * 1000),
                new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000),
                3005,
                103,
                3208,
                true
        );

        mockMvc.perform(
                post("/voting")
                        .content(gson.toJson(voting))
        ).andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString(
                                "/voting"))
                );
    }
}
