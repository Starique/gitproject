package com.tarique.webportal.utils;

import com.tarique.webportal.backend.persistence.domain.backend.User;

/**
 * Created by Mehnuma on 1/16/2017.
 */
public class UserUtils {
    private UserUtils() {
        throw new AssertionError("Not instantiable!!");
    }
    /**
     * @return
     */
    public static User createBasicUser() {
        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789123");
        user.setCountry("USA");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }
}
