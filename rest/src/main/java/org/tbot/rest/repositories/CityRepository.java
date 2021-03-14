package org.tbot.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tbot.rest.entities.City;

@RepositoryRestResource(collectionResourceRel = "city", path = "city")
public interface CityRepository extends PagingAndSortingRepository<City, String> {
    City getCityByCityName(@Param("cityName") String cityName);
    City getCityByCityNameAndArea(@Param("cityName") String cityName, @Param("area") String area);
}
