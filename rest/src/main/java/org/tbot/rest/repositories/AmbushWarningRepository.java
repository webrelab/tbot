package org.tbot.rest.repositories;

import org.tbot.rest.entities.AmbushWarning;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ambush-warning", path =
        "ambush-warning")
public interface AmbushWarningRepository extends PagingAndSortingRepository<AmbushWarning
        , String> {
}
