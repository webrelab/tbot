package org.tbot.rest.repositories;

import org.tbot.rest.entities.FormedGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "formed-group", path = "formed-group")
public interface FormedGroupRepository extends PagingAndSortingRepository<FormedGroup, String> {
}
