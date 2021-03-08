package org.tbot.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tbot.rest.entities.Action;

@RepositoryRestResource(collectionResourceRel = "action", path = "action")
public interface ActionRepository extends PagingAndSortingRepository<Action, String> {
}
