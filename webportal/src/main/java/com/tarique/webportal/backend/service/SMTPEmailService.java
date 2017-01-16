package com.tarique.webportal.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Mehnuma on 1/5/2017.
 */
public class SMTPEmailService extends AbstractEmailService {
    /** The Application logger */
    private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

    @Autowired
    MailSender mailSender;

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage simpleMailMessage) {
        LOG.debug("Sending generic email message by SMTP ...");
        mailSender.send(simpleMailMessage);
        LOG.debug("Done sending SMTP mail ...");
    }
}
