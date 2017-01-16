package com.tarique.webportal.test.integration;

import com.tarique.webportal.WebportalApplication;
import com.tarique.webportal.backend.persistence.domain.backend.Plan;
import com.tarique.webportal.backend.persistence.domain.backend.Role;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.domain.backend.UserRole;
import com.tarique.webportal.backend.service.UserService;
import com.tarique.webportal.enums.PlanEnum;
import com.tarique.webportal.enums.RoleEnum;
import com.tarique.webportal.utils.UserUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mehnuma on 1/16/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebportalApplication.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User basicUser = UserUtils.createBasicUser();
        Role basicRole = new Role(RoleEnum.BASIC);

        UserRole userRole = new UserRole(basicUser, basicRole);
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        basicUser = userService.createUser(basicUser, PlanEnum.BASIC_PLAN, userRoles);
        Assert.assertNotNull(basicUser);

    }
}
