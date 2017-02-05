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
        user.setFirstName("Tarique");
        user.setLastName("Syed");
        user.setPhoneNumber("(516)681-3921");
        user.setCountry("USA");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://s.gravatar.com/avatar/b8daabc029a0c3fce081230907c1ece3?s=80");
        return user;
    }
}
