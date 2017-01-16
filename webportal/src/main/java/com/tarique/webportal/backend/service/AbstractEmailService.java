package com.tarique.webportal.backend.service;

import com.tarique.webportal.web.domain.frontend.FeedBackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Mehnuma on 1/5/2017.
 * This is one of the implementation of the GoF adaoter pattern
 *
 */
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.to.address}")
    private String defaultToAddress;
    /**
     * Create a Simple Mail Message from a feedback Pojo
     * @param feedBackPojo
     * @return
     */
    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedBackPojo feedBackPojo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(feedBackPojo.getEmail());
        message.setSubject("[Tarique's Webportal]:Feedback received from " + feedBackPojo.getFirstName() + " " + feedBackPojo.getLastName() + "!");
        message.setText(feedBackPojo.getFeedback());
        return message;
    }

    /**
     * This message invoked the sendGenericEmailMessage which is not defined in this class
     * this is the implementation of the GoF Template Design Pattern this is possible because
     * this is a abstract class therefore the method defination is taken from the interface
     * @param feedBackPojo The feed back pojo
     */
    @Override
    public void sendFeedbackEmail(FeedBackPojo feedBackPojo) {
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedBackPojo));
    }
}
