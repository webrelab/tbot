package org.example.rest.repositories;

import org.example.rest.entities.Voting;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "voting", path = "voting")
public interface VotingRepository extends PagingAndSortingRepository<Voting,
        String> {
}
