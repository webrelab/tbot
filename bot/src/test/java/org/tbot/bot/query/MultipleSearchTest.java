package org.tbot.bot.query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.Roles;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.UserRepository;

import java.util.List;

public class MultipleSearchTest extends EntityRestCreationTest {
    private String cityId1;

    @Before
    public void before() {
        final City city1 = new City(
                faker.address().cityName(),
                faker.address().state(),
                faker.address().state(),
                faker.number().numberBetween(300000, 1800000),
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180)
        );

        cityId1 = RestRequest.post(CityRepository.class, city1)
                .orElseThrow(HTTPError::new);

        final City city2 = new City(
                faker.address().cityName(),
                faker.address().state(),
                faker.address().state(),
                faker.number().numberBetween(300000, 1800000),
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180)
        );

        final String cityId2 = RestRequest.post(CityRepository.class, city2)
                .orElseThrow(HTTPError::new);

        final User user1 = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.FUNCTIONARY.name(),
                cityId1
        );

        final User user2 = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.FUNCTIONARY.name(),
                cityId2
        );

        final User user3 = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.RESIDENT.name(),
                cityId1
        );

        final User user4 = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.RESIDENT.name(),
                cityId2
        );

        final User user5 = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.FUNCTIONARY.name(),
                cityId1
        );
        RestRequest.post(UserRepository.class, user1);
        RestRequest.post(UserRepository.class, user2);
        RestRequest.post(UserRepository.class, user3);
        RestRequest.post(UserRepository.class, user4);
        RestRequest.post(UserRepository.class, user5);
    }

    @Test
    public void userMultipleSearchTest() {
        final List<Pair<User>> userList1 = RestSearch.searchList(
                UserRepository.class,
                User.class,
                QueryNames.USERS_SEARCH_BY_ROLE_AND_CITY_ID,
                Roles.FUNCTIONARY.name(),
                cityId1
        );
        Assert.assertEquals(2, userList1.size());
        Assert.assertEquals(Roles.FUNCTIONARY.name(), userList1.get(0).getEntity().getRole());
        Assert.assertEquals(cityId1, userList1.get(1).getEntity().getCityId());

        final List<Pair<User>> userList2 = RestSearch.searchList(
                UserRepository.class,
                User.class,
                QueryNames.USERS_SEARCH_BY_CITY_ID,
                cityId1
        );

        Assert.assertEquals(3, userList2.size());
        Assert.assertEquals(cityId1, userList2.get(2).getEntity().getCityId());
    }
}
