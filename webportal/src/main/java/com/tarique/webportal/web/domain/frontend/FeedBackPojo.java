package com.tarique.webportal.web.domain.frontend;

import java.io.Serializable;

/**
 * Created by Mehnuma on 1/2/2017.
 */
public class FeedBackPojo implements Serializable{

    private static final long serialVersionUID = 1L;
    private String email = "emailm";
    private String firstName = "firstname";
    private String lastName  = "lastname";
    private String feedback  = "feedback";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "FeedBackPojo{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }

}
