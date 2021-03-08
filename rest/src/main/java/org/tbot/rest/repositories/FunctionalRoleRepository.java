package org.tbot.rest.repositories;

import org.tbot.rest.entities.FunctionalRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "functional-role", path =
        "functional-role")
public interface FunctionalRoleRepository extends PagingAndSortingRepository<FunctionalRole, String> {
}
