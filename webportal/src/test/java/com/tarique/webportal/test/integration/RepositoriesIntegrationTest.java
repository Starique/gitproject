package com.tarique.webportal.test.integration;

import com.tarique.webportal.WebportalApplication;
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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mehnuma on 1/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebportalApplication.class)
public class RepositoriesIntegrationTest {

    @Autowired
    PlanRepository planRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void init() {
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception {
        Plan basicplan = createBasicPlan(PlanEnum.BASIC_PLAN);
        planRepository.save(basicplan);

        Plan retrievedPlan = planRepository.findOne(PlanEnum.BASIC_PLAN.getId());
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Role basicRole = createBasicRole(RoleEnum.BASIC);
        roleRepository.save(basicRole);

        Role retrievedRole = roleRepository.findOne(RoleEnum.BASIC.getId());
        Assert.assertNotNull(retrievedRole);


    }

    @Test
    public void testCreateNewUser() throws Exception {
        Plan plan = createBasicPlan(PlanEnum.BASIC_PLAN);
        planRepository.save(plan);

        User basicUser = UserUtils.createBasicUser();
        basicUser.setPlan(plan);

        Role basicRole = createBasicRole(RoleEnum.BASIC);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for (UserRole role : userRoles) {
            Role role1 = role.getRole();
            roleRepository.save(role1);
        }
        basicUser = userRepository.save(basicUser);

        User retrievedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(retrievedUser);

        Assert.assertTrue(retrievedUser.getId() != 0);
        Assert.assertNotNull(retrievedUser.getPlan());
        Set<UserRole> userroels = retrievedUser.getUserRoles();
        for (UserRole ur : userroels) {
            Assert.assertNotNull(ur.getRole());
        }

    }


    /**
     * @return
     */
    private Role createBasicRole(RoleEnum roleEnum) {
        return new Role(roleEnum);
    }

    /**
     * @return
     */
    private Plan createBasicPlan(PlanEnum planEnum) {
       return new Plan(planEnum);
    }

}
