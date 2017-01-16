package com.tarique.webportal.backend.persistence.repositories;

import com.tarique.webportal.backend.persistence.domain.backend.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Mehnuma on 1/14/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
