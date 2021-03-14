package org.tbot.bot.query;

import org.junit.Assert;
import org.junit.Test;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EntityNotFoundRequestTest {

    @Test
    public void getNotFound() {
        final Optional<User> user = RestRequest.get(
                UserRepository.class, User.class, UUID.randomUUID().toString()
        );
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void singleSearchEmpty() {
        final Optional<Pair<User>> user = RestSearch.searchSingle(
                UserRepository.class,
                User.class,
                QueryNames.USER_SEARCH_BY_CHAT_ID,
                "86465487696858"
        );

        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void multipleSearchEmpty() {
        final List<Pair<User>> userList = RestSearch.searchList(
                UserRepository.class,
                User.class,
                QueryNames.USERS_SEARCH_BY_CITY_ID,
                UUID.randomUUID().toString()
        );

        Assert.assertTrue(userList.isEmpty());
    }
}
