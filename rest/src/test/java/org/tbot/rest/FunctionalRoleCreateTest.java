package org.tbot.rest;

import org.tbot.rest.entities.FunctionalRole;
import org.tbot.rest.repositories.FunctionalRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalRoleCreateTest extends EntityCreateTests {
    @Autowired
    private FunctionalRoleRepository functionalRoleRepository;

    @BeforeEach
    @AfterEach
    public void clear() {
        functionalRoleRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() throws Exception {
        final FunctionalRole functionalRole = new FunctionalRole(
                "Может просматривать контент",
                "Описание",
                "Условия получения"
        );

        mockMvc.perform(
                post("/functional-role")
                .content(gson.toJson(functionalRole))
        ).andExpect(status().isCreated())
                .andExpect(
                        header().string("Location", containsString(
                                        "/functional-role"))
                );
    }
}
