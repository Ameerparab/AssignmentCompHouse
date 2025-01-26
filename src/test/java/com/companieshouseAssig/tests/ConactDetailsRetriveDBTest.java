package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class ConactDetailsRetriveDBTest extends BaseClass {

	@Test(priority = 1)
	public void sendContactDetails() {
		String cntName = prop.getProperty("name").trim();
		String cntEmail = prop.getProperty("email").trim();
		String cntPhone = prop.getProperty("phone").trim();
		String cntSubject = prop.getProperty("subject").trim();
		String cntMessage = prop.getProperty("message").trim();
		contactSend.submitContactDetails(cntName, cntEmail, cntPhone, cntSubject, cntMessage);
	}

	@Test(priority = 2)
	public void verfiySubmitedMessage() {
		String messg = contactSend.verfiyThanksMessage();
		System.out.println(messg);
		Assert.assertTrue(messg.contains("Thanks for getting in touch"));
	}

	@Test(priority = 3)
	public void loginadmin() throws InterruptedException {
		homePage.adminClick();
		loginPage.adminLoginMessage();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		loginPage.doLogin(username, password);
		String logOutOpt = adminPage.logOutOption();
		Assert.assertTrue(logOutOpt.contains("Logout"));
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void verfiyDataEntry() throws InterruptedException {
		String cntName = prop.getProperty("name").trim();
		String cntSubject = prop.getProperty("subject").trim();
		boolean status = contactSend.verfiyDataEntryMessage(cntName, cntSubject);
		Assert.assertTrue(status);
	}
}