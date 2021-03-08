package org.tbot.rest.repositories;

import org.tbot.rest.entities.RoleRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "role-request", path = "role-request")
public interface RoleRequestRepository extends PagingAndSortingRepository<RoleRequest, String> {
}
