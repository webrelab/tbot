package org.tbot.bot.query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.Roles;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.UserRepository;

public class UserRestCreationTest extends EntityRestCreationTest {
    private String cityId;

    @Before
    public void prepareData() {
        final City city = new City(
                faker.address().cityName(),
                faker.address().state(),
                faker.number().numberBetween(300000, 1800000),
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180)
        );
        cityId = RestRequest.post(CityRepository.class, city);
    }

    @Test
    public void entityShouldBeCreated() {
            final User user = new User(
                    String.valueOf(faker.number().randomNumber(10, true)),
                    Roles.RESIDENT.name(),
                    cityId
            );
            final String userId = RestRequest.post(UserRepository.class, user);
            final User getUser = RestRequest.getById(UserRepository.class, User.class, userId);
            Assert.assertEquals(user.getChatId(), getUser.getChatId());
    }
}
