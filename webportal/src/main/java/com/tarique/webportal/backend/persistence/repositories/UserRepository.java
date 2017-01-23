package com.tarique.webportal.backend.persistence.repositories;

import com.tarique.webportal.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mehnuma on 1/14/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Returns an User with the given username, null if not found
     * @param username The User Name
     * @return User given the user name null if not found
     */
    public User findByUsername(String username);
}
