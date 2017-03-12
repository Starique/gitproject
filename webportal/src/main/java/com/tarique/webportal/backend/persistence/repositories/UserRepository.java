package com.tarique.webportal.backend.persistence.repositories;

import com.tarique.webportal.backend.persistence.domain.backend.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mehnuma on 1/14/2017.
 */
@Transactional(readOnly = true)
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Returns an User with the given username, null if not found
     * @param username The User Name
     * @return User given the user name null if not found
     */
    User findByUsername(String username);


    /**
     * Returns an User with the given email, null if not found
     * @param email the email address
     * @return User given the email address
     */
    User findByEmail(String email);

    /**
     *
     * @param userId user Id
     * @param password new password
     */
    @Modifying
    @Query("update User u set u.password = :password where u.id = :userId")
    void updateUserPassword(@Param("userId") long userId, @Param("password") String password);
}
