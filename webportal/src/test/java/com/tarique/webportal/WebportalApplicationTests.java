package com.tarique.webportal;

import com.tarique.webportal.com.tarique.webportal.web.i18n.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebportalApplicationTests {

	@Autowired
	I18NService i18NService;

	@Test
	public void testMessageByLocalService() throws  Exception {
		String expectedResult = "Tarique's Webportal Bootstrap Template";
		String messageId = "index.main.callout";
		String actual = i18NService.getMessage(messageId);
		Assert.assertEquals("The Actual message does not match", expectedResult, actual);
	}
}
