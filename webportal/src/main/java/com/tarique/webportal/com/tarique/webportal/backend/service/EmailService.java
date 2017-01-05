package com.tarique.webportal.com.tarique.webportal.backend.service;

import com.tarique.webportal.com.tarique.webportal.web.domain.frontend.FeedBackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Mehnuma on 1/2/2017.
 * This is a implementation of GoF (goof) Strategy pattern
 */

public interface EmailService {
    /**
     * Send an email with the content in the Feedback pojo
     * @param feedBackPojo The feed back pojo
     */
    public void sendFeedbackEmail(FeedBackPojo feedBackPojo);

    /**
     * Send an email with the content of the simple Mail message object.
     * @param simpleMailMessage the object containing the email messae
     */

    public void sendGenericEmailMessage(SimpleMailMessage simpleMailMessage);


}
