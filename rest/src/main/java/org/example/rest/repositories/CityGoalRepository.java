package org.example.rest.repositories;

import org.example.rest.entities.CityGoal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "city-goals", path = "city-goal")
public interface CityGoalRepository extends PagingAndSortingRepository<CityGoal, String> {
}
