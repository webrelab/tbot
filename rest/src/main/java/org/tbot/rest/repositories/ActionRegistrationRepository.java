package org.tbot.rest.repositories;

import org.tbot.rest.entities.ActionRegistration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "action-registration", path = "action-registration")
public interface ActionRegistrationRepository extends PagingAndSortingRepository<ActionRegistration, String> {
}
