package com.tarique.webportal.com.tarique.webportal.web.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by Mehnuma on 12/26/2016.
 */
@Service
public class I18NService {

    @Autowired
    private MessageSource messageSource;

    /**
     * Returns a message for a given messageId and the default locale in the session context
     * @param messageId the key to the message resource file
     */
    public String getMessage(String messageId){
        Locale locale  = LocaleContextHolder.getLocale();
        return getMessage(messageId, locale);
    }

    /**
     * Returns a message of the given messageID and Locale
     * @param messageId  key to the messagees resource file
     * @param locale The locale
     */

    public String getMessage(String messageId, Locale locale){
        return messageSource.getMessage(messageId, null, locale);
    }
}
