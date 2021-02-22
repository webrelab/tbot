package org.example.rest;

import com.google.gson.Gson;
import org.example.rest.entities.City;
import org.example.rest.entities.Roles;
import org.example.rest.entities.User;
import org.example.rest.repositories.CityRepository;
import org.example.rest.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class UserCreateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void clearAll() {
        userRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @AfterEach
    public void clearAllAfter() {
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
                "847457969",
                Roles.CITIZEN.getRoleName(),
                cityId
        );

        mockMvc.perform(post("/user")
                .content(new Gson().toJson(userData)))
                .andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString("user/"))
                );
    }
}
