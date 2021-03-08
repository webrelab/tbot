package org.tbot.bot.query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tbot.rest.entities.*;
import org.tbot.rest.repositories.ActionRepository;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.FormedGroupRepository;
import org.tbot.rest.repositories.UserRepository;

import java.util.Date;

public class XrefFormedGroupUserRestCreationTest extends EntityRestCreationTest {
    private String formedGroupId;
    private String userId;
    @Before
    public void prepareData() {
        final City city = new City(
                faker.address().cityName(),
                faker.address().state(),
                faker.number().numberBetween(300000, 1800000),
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180)
        );
        final String cityId = RestRequest.post(CityRepository.class, city);
        final User user = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.RESIDENT.name(),
                cityId
        );
        userId = RestRequest.post(UserRepository.class, user);
        final Action action = new Action(
                cityId,
                new Date(),
                new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)
        );
        final String actionId = RestRequest.post(ActionRepository.class, action);
        final FormedGroup formedGroup = new FormedGroup(
                cityId,
                actionId,
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180),
                faker.address().fullAddress(),
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180),
                faker.address().fullAddress(),
                faker.number().numberBetween(300, 800)
        );
        formedGroupId = RestRequest.post(FormedGroupRepository.class, formedGroup);
    }
    @Test
    public void referenceEntitiesTest() {
        RestRequest.putFormedGroupToUserAssociation(formedGroupId, userId);
        final User getUser = RestRequest.get(
                FormedGroupRepository.class,
                User.class,
                formedGroupId,
                FormedGroup.getUserRelationName(),
                userId
        );
        final FormedGroup getFormedGroup = RestRequest.get(
                UserRepository.class,
                FormedGroup.class,
                userId,
                User.getFormedGroupRelationName(),
                formedGroupId
        );
        Assert.assertNotNull(getUser);
        Assert.assertNotNull(getFormedGroup);
    }
}
