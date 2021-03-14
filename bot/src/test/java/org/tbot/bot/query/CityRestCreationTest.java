package org.tbot.bot.query;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.tbot.rest.entities.City;
import org.tbot.rest.repositories.CityRepository;

@Slf4j
public class CityRestCreationTest extends EntityRestCreationTest {

    @Test
    public void entityShouldBeCreated() {
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
        final City getCity = RestRequest.getById(CityRepository.class, City.class, cityId)
                .orElseThrow(HTTPError::new);
        Assert.assertEquals(city.getCityName(), getCity.getCityName());
    }
}
