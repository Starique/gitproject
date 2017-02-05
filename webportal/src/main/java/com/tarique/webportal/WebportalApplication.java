package com.tarique.webportal;

import com.tarique.webportal.backend.persistence.domain.backend.Role;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.domain.backend.UserRole;
import com.tarique.webportal.backend.service.UserService;
import com.tarique.webportal.enums.PlanEnum;
import com.tarique.webportal.enums.RoleEnum;
import com.tarique.webportal.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WebportalApplication implements CommandLineRunner{

	/** The Application logger */
	private static final Logger LOG = LoggerFactory.getLogger(WebportalApplication.class);

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(WebportalApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		String userName = "proUser";
		String email   = userName + "@gmail.com";

		User user = UserUtils.createBasicUser(userName, email);
		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole(user, new Role(RoleEnum.PRO));
		userRoleSet.add(userRole);
		LOG.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, PlanEnum.BASIC_PLAN, userRoleSet);
		LOG.debug("User created {}", user.getUsername());
	}
}
