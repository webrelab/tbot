package org.example.rest.repositories;

import org.example.rest.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "users", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
