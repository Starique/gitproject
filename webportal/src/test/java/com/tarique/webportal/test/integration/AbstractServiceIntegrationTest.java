package com.tarique.webportal.test.integration;

import com.tarique.webportal.backend.persistence.domain.backend.Role;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.domain.backend.UserRole;
import com.tarique.webportal.backend.service.UserService;
import com.tarique.webportal.enums.PlanEnum;
import com.tarique.webportal.enums.RoleEnum;
import com.tarique.webportal.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tariquedev on 3/12/17.
 */
public class AbstractServiceIntegrationTest {
    @Autowired
    protected UserService userService;

    protected User createUser(TestName testName) {
        String userName = testName.getMethodName();
        String email    = userName + "@gmail.com";

        User basicUser = UserUtils.createBasicUser(userName, email);
        Role basicRole = new Role(RoleEnum.BASIC);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        return userService.createUser(basicUser, PlanEnum.BASIC_PLAN, userRoles);
    }
}
