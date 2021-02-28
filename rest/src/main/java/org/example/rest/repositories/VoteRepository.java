package org.example.rest.repositories;

import org.example.rest.entities.Vote;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "votes", path = "vote")
public interface VoteRepository extends PagingAndSortingRepository<Vote, String> {
}
