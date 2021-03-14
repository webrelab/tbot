package org.tbot.bot.query;

import org.junit.Assert;
import org.junit.Test;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.Roles;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.UserRepository;

import java.util.Optional;

public class SingleSearchTest extends EntityRestCreationTest {

    @Test
    public void userSingleSearchTest() {
        final City city = new City(
                faker.address().cityName(),
                faker.address().state(),
                faker.address().state(),
                faker.number().numberBetween(300000, 1800000),
                faker.number().randomDouble(5, -180, 180),
                faker.number().randomDouble(5, -180, 180)
        );

        final String cityId = RestRequest.post(CityRepository.class, city)
                .orElseThrow(HTTPError::new);

        final User user = new User(
                String.valueOf(faker.number().randomNumber(10, true)),
                Roles.RESIDENT.name(),
                cityId
        );
        RestRequest.post(UserRepository.class, user);

        final Optional<Pair<User>> searchedUser = RestSearch.searchSingle(
                UserRepository.class,
                User.class,
                QueryNames.USER_SEARCH_BY_CHAT_ID,
                user.getChatId()
        );
        Assert.assertTrue(searchedUser.isPresent());
        Assert.assertEquals(user.getChatId(), searchedUser.get().getEntity().getChatId());
    }
}
