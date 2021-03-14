package org.tbot.rest.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.tbot.rest.entities.User;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User getUserByChatId(@Param("chatId") String chatId);
    List<User> getUsersByRole(@Param("role") String role);
    List<User> getUsersByCityId(@Param("cityId") String cityId);
    List<User> getUsersByRoleAndCityId(@Param("role") String role, @Param("cityId") String cityId);
}
