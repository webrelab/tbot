package org.example.rest.repositories;

import org.example.rest.entities.FunctionalRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "functional-roles", path =
        "functional-role")
public interface FunctionalRoleRepository extends PagingAndSortingRepository<FunctionalRole, String> {
}
