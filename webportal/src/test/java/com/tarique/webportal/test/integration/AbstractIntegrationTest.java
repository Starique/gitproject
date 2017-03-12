package com.tarique.webportal.test.integration;

import com.tarique.webportal.backend.persistence.domain.backend.Plan;
import com.tarique.webportal.backend.persistence.domain.backend.Role;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.domain.backend.UserRole;
import com.tarique.webportal.backend.persistence.repositories.PlanRepository;
import com.tarique.webportal.backend.persistence.repositories.RoleRepository;
import com.tarique.webportal.backend.persistence.repositories.UserRepository;
import com.tarique.webportal.enums.PlanEnum;
import com.tarique.webportal.enums.RoleEnum;
import com.tarique.webportal.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tariquedev on 3/11/17.
 */
public class AbstractIntegrationTest {
    @Autowired
    PlanRepository planRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * @return
     */
    protected Role createRole(RoleEnum roleEnum) {
        return new Role(roleEnum);
    }

    /**
     * @return
     */
    protected Plan createPlan(PlanEnum planEnum) {
        return new Plan(planEnum);
    }

    protected User createUser(String userName, String email) {
        Plan basicPlan = createPlan(PlanEnum.BASIC_PLAN);
        planRepository.save(basicPlan);

        User basicUser = UserUtils.createBasicUser(userName, email);
        basicUser.setPlan(basicPlan);

        Role basicRole = createRole(RoleEnum.BASIC);
        roleRepository.save(basicRole);

        Set<UserRole> userRoles = new HashSet<>();

        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRepository.save(basicUser);
        return basicUser;
    }
    protected User createUser(TestName testName){
        return createUser(testName.getMethodName(), testName.getMethodName() + "gmail.com");
    }
}
