package org.example.rest.repositories;

import org.example.rest.entities.RoleRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "role-requests", path = "role-request")
public interface RoleRequestRepository extends PagingAndSortingRepository<RoleRequest, String> {
}
