package org.example.rest;

import org.example.rest.entities.*;
import org.example.rest.repositories.CityRepository;
import org.example.rest.repositories.FunctionalRoleRepository;
import org.example.rest.repositories.RoleRequestRepository;
import org.example.rest.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoleRequestCreateTest extends EntityCreateTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRequestRepository roleRequestRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FunctionalRoleRepository functionalRoleRepository;

    @BeforeEach
    @AfterEach
    public void clearAll() {
        roleRequestRepository.deleteAll();
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
        final User userData = new User(
                "6437658769",
                Roles.RESIDENT.getRoleName(),
                functionalRoleId,
                cityId
        );
        final String userId = userRepository.save(userData).getUserId();

        final RoleRequest roleRequest = new RoleRequest(
                Roles.CITIZEN.getRoleName(),
                userId,
                "/var/data/downloaded/ugfosdfhhigo/suhodhigso.mp4",
                "Описание запроса роли. Описание запроса роли. Описание запроса роли. Описание запроса роли. " +
                        "Описание запроса роли. Описание запроса роли. Описание запроса роли. Описание запроса роли. " +
                        "Описание запроса роли. Описание запроса роли. Описание запроса роли. Описание запроса роли. " +
                        "Описание запроса роли. Описание запроса роли. Описание запроса роли. Описание запроса роли. " +
                        "Описание запроса роли. Описание запроса роли. Описание запроса роли. Описание запроса роли. " +
                        "Описание запроса роли. Описание запроса роли. Описание запроса роли. Описание запроса роли. "
        );

        mockMvc.perform(post("/role-request")
                .content(gson.toJson(roleRequest)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("role-request/"))
                );
    }
}
