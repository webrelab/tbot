package org.tbot.bot.resolvers;

import org.tbot.bot.query.Pair;
import org.tbot.bot.query.QueryNames;
import org.tbot.bot.query.RestSearch;
import org.tbot.rest.entities.City;
import org.tbot.rest.repositories.CityRepository;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public class CityBotResolver {
    private final Update update;

    public CityBotResolver(final Update update) {
        this.update = update;
    }

    public boolean checkIfCityExist(final String cityName, final String area) {
        final Optional<Pair<City>> city = RestSearch.searchSingle(
                CityRepository.class,
                City.class,
                QueryNames.CITY_SEARCH_BY_CITY_NAME_AND_AREA,
                cityName,
                area
        );
        return city.isPresent();
    }
}
