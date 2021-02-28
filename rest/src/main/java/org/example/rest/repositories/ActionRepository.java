package org.example.rest.repositories;

import org.example.rest.entities.Action;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "actions", path = "action")
public interface ActionRepository extends PagingAndSortingRepository<Action, String> {
}
