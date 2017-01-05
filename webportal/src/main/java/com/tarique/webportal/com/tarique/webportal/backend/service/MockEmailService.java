package com.tarique.webportal.com.tarique.webportal.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Mehnuma on 1/5/2017.
 */
public class MockEmailService extends AbstractEmailService {
    /** The Application logger */
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage simpleMailMessage) {
        LOG.debug("Simulating an email message ...");
        LOG.info(simpleMailMessage.toString());
        LOG.debug("Mock Email Sent ...");
    }
}
