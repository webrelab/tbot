package org.example.rest.repositories;

import org.example.rest.entities.ActionRegistration;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "action-registrations", path = "action-registration")
public interface ActionRegistrationRepository extends PagingAndSortingRepository<ActionRegistration, String> {
}
