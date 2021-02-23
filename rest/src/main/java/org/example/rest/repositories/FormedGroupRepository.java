package org.example.rest.repositories;

import org.example.rest.entities.FormedGroup;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "formed-groups", path = "formed-group")
public interface FormedGroupRepository extends PagingAndSortingRepository<FormedGroup, String> {
}
