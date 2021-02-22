package org.example.rest.repositories;

import org.example.rest.entities.City;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cities", path = "city")
public interface CityRepository extends PagingAndSortingRepository<City, String> {
}
