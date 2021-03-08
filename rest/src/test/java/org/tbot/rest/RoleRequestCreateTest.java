package org.tbot.rest;

import org.tbot.rest.entities.City;
import org.tbot.rest.entities.RoleRequest;
import org.tbot.rest.entities.Roles;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.RoleRequestRepository;
import org.tbot.rest.repositories.UserRepository;
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

    @BeforeEach
    @AfterEach
    public void clearAll() {
        roleRequestRepository.deleteAll();
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
