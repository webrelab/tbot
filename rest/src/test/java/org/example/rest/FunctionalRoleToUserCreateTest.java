package org.example.rest;

import org.example.rest.entities.City;
import org.example.rest.entities.FunctionalRole;
import org.example.rest.entities.Roles;
import org.example.rest.entities.User;
import org.example.rest.repositories.CityRepository;
import org.example.rest.repositories.FunctionalRoleRepository;
import org.example.rest.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalRoleToUserCreateTest extends EntityCreateTests {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FunctionalRoleRepository functionalRoleRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
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
        final User user = new User(
                "847457969",
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
