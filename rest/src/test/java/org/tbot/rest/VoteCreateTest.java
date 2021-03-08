package org.tbot.rest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbot.rest.entities.*;
import org.tbot.rest.repositories.*;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FunctionalRoleRepository functionalRoleRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @BeforeEach
    @AfterEach
    public void clean() {
        voteRepository.deleteAll();
        votingRepository.deleteAll();
        userRepository.deleteAll();
        functionalRoleRepository.deleteAll();
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
        final FunctionalRole functionalRole = new FunctionalRole(
                "Может просматривать контент",
                "Описание",
                "Условия получения"
        );
        final String functionalRoleId =
                functionalRoleRepository.save(functionalRole).getFunctionalRoleId();
        final User user = new User(
                "847457969",
                Roles.CITIZEN.name(),
                cityId
        );
        final String userId = userRepository.save(user).getUserId();
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
        final String votingId = votingRepository.save(voting).getVotingId();

        final Vote vote = new Vote(
                votingId,
                userId,
                true,
                53.2436345,
                52.34562334,
                new Date()
        );

        mockMvc.perform(
                post("/vote")
                        .content(gson.toJson(vote))
        ).andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString(
                                "/vote"))
                );
    }
}
