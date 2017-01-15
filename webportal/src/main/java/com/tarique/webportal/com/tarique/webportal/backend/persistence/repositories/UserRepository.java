package com.tarique.webportal.com.tarique.webportal.backend.persistence.repositories;

import com.tarique.webportal.com.tarique.webportal.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mehnuma on 1/14/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
