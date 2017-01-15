package com.tarique.webportal.test.integration;

import com.tarique.webportal.WebportalApplication;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.domain.backend.Plan;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.domain.backend.Role;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.domain.backend.UserRole;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.repositories.PlanRepository;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.repositories.RoleRepository;
import com.tarique.webportal.com.tarique.webportal.backend.persistence.repositories.UserRepository;
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

    private static final int BASIC_PLAN_ID = 1;
    private static final int USER_ROLE = 3;


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
        Plan basicplan = createBasicPlan();
        planRepository.save(basicplan);

        Plan retrievedPlan = planRepository.findOne(BASIC_PLAN_ID);
        Assert.assertNotNull(retrievedPlan);
    }

    @Test
    public void testCreateNewRole() throws Exception {
        Role basicRole = createBasicRole();
        roleRepository.save(basicRole);

        Role retrievedRole = roleRepository.findOne(USER_ROLE);
        Assert.assertNotNull(retrievedRole);


    }

    @Test
    public void testCreateNewUser() throws Exception {
        Plan plan = createBasicPlan();
        planRepository.save(plan);

        User basicUser = createBasicUser();
        basicUser.setPlan(plan);

        Role basicRole = createBasicRole();

        Set<UserRole> userRoles = new HashSet<>();

        UserRole userRole = new UserRole();
        userRole.setUser(basicUser);
        userRole.setRole(basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for(UserRole role: userRoles){
           Role role1 = role.getRole();
           roleRepository.save(role1);
        }
        basicUser = userRepository.save(basicUser);

        User retrievedUser = userRepository.findOne(basicUser.getId());
        Assert.assertNotNull(retrievedUser);

        Assert.assertTrue(retrievedUser.getId()!= 0);
        Assert.assertNotNull(retrievedUser.getPlan());
        Set<UserRole> userroels = retrievedUser.getUserRoles();
        for(UserRole ur : userroels){
            Assert.assertNotNull(ur.getRole());
        }

    }

    /**
     *
     * @return
     */
    private User createBasicUser() {
        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789123");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }

    /**
     *
     * @return
     */
    private Role createBasicRole() {
        Role role = new Role();
        role.setId(USER_ROLE);
        return role;
    }

    /**
     *
     * @return
     */
    private Plan createBasicPlan() {
        Plan plan = new Plan();
        plan.setId(BASIC_PLAN_ID);
        return plan;
    }

}
