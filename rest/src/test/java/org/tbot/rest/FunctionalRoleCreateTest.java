package org.tbot.rest;

import org.junit.jupiter.api.Test;
import org.tbot.rest.entities.FunctionalRole;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FunctionalRoleCreateTest extends EntityCreateTests {

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
