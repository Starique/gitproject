package com.tarique.webportal.backend.service;

import com.tarique.webportal.backend.persistence.domain.backend.Plan;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.domain.backend.UserRole;
import com.tarique.webportal.backend.persistence.repositories.PlanRepository;
import com.tarique.webportal.backend.persistence.repositories.RoleRepository;
import com.tarique.webportal.backend.persistence.repositories.UserRepository;
import com.tarique.webportal.enums.PlanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Mehnuma on 1/16/2017.
 *
 * Step 1 annotate with @service annotation
 * Step 2 define method that we want to service to do
 * Step 3 Autowire the repository classes
 * Step 4 Annotate the class with @transactional readonly=true
 *        This is a best practice for jpa. By default we should use @transactional with readonly
 *        to minimize locking and then use @Transactional with no arguments for the methods which
 *        update and write to the database. Although the JPA repositories are transactional by default
 *        placing hte @transactional on method overrides the jpa transactional.
 * Step 5
 */
@Service


@Transactional(readOnly = true)

public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User createUser(User user, PlanEnum planEnum, Set<UserRole> userRoleSet) {

        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        Plan  plan = new Plan(planEnum);
        //Make sure the paln is in the databse otherwise save it
        if (!planRepository.exists(plan.getId())) {
            planRepository.save(plan);
        }
        //Add plan to the user
        user.setPlan(plan);

        //Save all the roles and add to user
        for(UserRole ur : userRoleSet){
            roleRepository.save(ur.getRole());
        }
        //Be careful here. For Spring Repository always get the list and then set
        user.getUserRoles().addAll(userRoleSet);

        //Finally save the user in the Database
        user = userRepository.save(user);
        return user;
    }

}
