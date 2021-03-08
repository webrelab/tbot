package org.tbot.rest.repositories;

import org.tbot.rest.entities.CityGoal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "city-goal", path = "city-goal")
public interface CityGoalRepository extends PagingAndSortingRepository<CityGoal, String> {
}
