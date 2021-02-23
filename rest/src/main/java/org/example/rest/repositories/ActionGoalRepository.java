package org.example.rest.repositories;

import org.example.rest.entities.ActionGoal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "action-goals", path = "action-goal")
public interface ActionGoalRepository extends PagingAndSortingRepository<ActionGoal, String> {
}
