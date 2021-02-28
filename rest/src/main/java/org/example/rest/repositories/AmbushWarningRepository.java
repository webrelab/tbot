package org.example.rest.repositories;

import org.example.rest.entities.AmbushWarning;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ambush-warnings", path =
        "ambush-warning")
public interface AmbushWarningRepository extends PagingAndSortingRepository<AmbushWarning
        , String> {
}
