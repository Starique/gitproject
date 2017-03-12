package com.tarique.webportal.test.integration;

import com.tarique.webportal.WebportalApplication;
import com.tarique.webportal.backend.persistence.domain.backend.PasswordResetToken;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.repositories.PasswordResetTokenRepository;
import com.tarique.webportal.backend.service.PasswordResetTokenService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tariquedev on 3/12/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebportalApplication.class)
public class PasswordResetTokenServiceIntegrationTest extends  AbstractServiceIntegrationTest{

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;


    @Rule public TestName testName = new TestName();

    @Test
    public void testCreateNewTokenForUserEmail() throws Exception {
        User user = createUser(testName);

        PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
        Assert.assertNotNull(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getToken());

    }
}
