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
     * Creates a user with basic attibutes
     * @param userName The User Name
     * @param email    The password
     * @return a User entity
     */
    public static User createBasicUser(String userName, String email) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword("secret");
        user.setEmail(email);
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
