package org.tbot.bot.resolvers;

import org.tbot.bot.query.*;
import org.tbot.bot.services.geo.GeoPosition;
import org.tbot.rest.entities.City;
import org.tbot.rest.entities.Roles;
import org.tbot.rest.entities.User;
import org.tbot.rest.repositories.CityRepository;
import org.tbot.rest.repositories.UserRepository;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public class UserBotResolver {
    private final Update update;

    public UserBotResolver(final Update update) {
        this.update = update;
    }

    public String registerUser() {
        final Optional<Pair<User>> loadUser = loadUser();
        if (loadUser.isPresent()) {
            return loadUser.get().getId();
        }
        final GeoPosition position = new GeoPosition(
                update.getMessage().getLocation().getLatitude(),
                update.getMessage().getLocation().getLongitude()
        );
        final Optional<Pair<City>> city = RestSearch.searchSingle(
                CityRepository.class,
                City.class,
                QueryNames.CITY_SEARCH_BY_CITY_NAME_AND_AREA,
                position.getCity(),
                position.getArea()
        );
        final String cityId;
        if (!city.isPresent()) {
            cityId = RestRequest.post(
                    CityRepository.class,
                    position.createCityEntity()
            ).orElseThrow(HTTPError::new);
        } else {
            cityId = city.get().getId();
        }
        final User user = new User(
                String.valueOf(update.getMessage().getChatId()),
                Roles.RESIDENT.name(),
                cityId
        );
        return RestRequest.post(
                UserRepository.class,
                user
        ).orElseThrow(HTTPError::new);
    }

    public Optional<Pair<User>> loadUser() {
        final String chatId = update.getMessage().getChatId().toString();
        return RestSearch.searchSingle(
                UserRepository.class,
                User.class,
                QueryNames.USER_SEARCH_BY_CHAT_ID,
                chatId
        );
    }

    public boolean checkIfUserExist() {
        return loadUser().isPresent();
    }

}
