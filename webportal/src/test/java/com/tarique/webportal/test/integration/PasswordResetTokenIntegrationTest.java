package com.tarique.webportal.test.integration;

import com.tarique.webportal.WebportalApplication;
import com.tarique.webportal.backend.persistence.domain.backend.PasswordResetToken;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.repositories.PasswordResetTokenRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Mehnuma on 1/14/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebportalApplication.class)
public class PasswordResetTokenIntegrationTest extends AbstractIntegrationTest {

    @Value("${token.expiration.length.minutes}")
    private int expirationTimeInMinutes;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Rule public TestName testName = new TestName();

    @Before
    public void init() {
        Assert.assertFalse(expirationTimeInMinutes==0);
    }
    @Test
    public void testTokenExpirationLength() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expectedExpTime = now.plusMinutes(expirationTimeInMinutes);
        String token = UUID.randomUUID().toString();

        PasswordResetToken passwordResetToken = createPasswordresetToken(token,user,now);
        LocalDateTime actualExpTime = passwordResetToken.getExpiryTime();
        Assert.assertNotNull(passwordResetToken);
        Assert.assertEquals(expectedExpTime, actualExpTime);
    }
    @Test
    public void testFindTokenByTokenValue() throws Exception {
        User user = createUser(testName);
        Assert.assertNotNull(user);

        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        createPasswordresetToken(token, user, now);

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        Assert.assertNotNull(passwordResetToken);
    }
    @Test
    public void testDeleteToken() throws Exception {
        User user = createUser(testName);
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        PasswordResetToken passwordResetToken = createPasswordresetToken(token,user,now);
        long tokenId = passwordResetToken.getId();

        passwordResetTokenRepository.delete(tokenId);

        PasswordResetToken shouldNotExists = passwordResetTokenRepository.findOne(tokenId);
        Assert.assertNull(shouldNotExists);
    }

    @Test
    public void testCascadeDeleteToken() throws Exception {
        User user = createUser(testName);
        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        PasswordResetToken passwordResetToken = createPasswordresetToken(token, user, now);
        userRepository.delete(user.getId());

        Set<PasswordResetToken> passwordResetTokenSet = passwordResetTokenRepository.findAllByUserId(user.getId());
        Assert.assertTrue(passwordResetTokenSet.isEmpty());
    }
    @Test
    public void testMultipleReturnedToken() throws Exception {
        User user = createUser(testName);
        LocalDateTime now = LocalDateTime.now();

        String token  = UUID.randomUUID().toString();
        String token1 = UUID.randomUUID().toString();
        String token2 = UUID.randomUUID().toString();

        Set<PasswordResetToken> tokens = new HashSet<>();

        tokens.add(createPasswordresetToken(token, user, now));
        tokens.add(createPasswordresetToken(token1, user, now));
        tokens.add(createPasswordresetToken(token2, user, now));

        passwordResetTokenRepository.save(tokens);

        User userFromDb = userRepository.findOne(user.getId());
        Set<PasswordResetToken> actualTokens = passwordResetTokenRepository.findAllByUserId(userFromDb.getId());

        Assert.assertTrue(tokens.size() == actualTokens.size());

        List<String> tokeAsList = tokens.stream().map(s->s.getToken()).collect(Collectors.toList());
        List<String> actualTokenAsList = actualTokens.stream().map(s->s.getToken()).collect(Collectors.toList());
        Assert.assertEquals(tokeAsList, actualTokenAsList);

    }


    //private Methods
    private PasswordResetToken createPasswordresetToken(String token, User user, LocalDateTime createTime){
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, createTime, expirationTimeInMinutes);
        passwordResetTokenRepository.save(passwordResetToken);
        Assert.assertNotNull(passwordResetToken.getId());
        return passwordResetToken;
    }

}