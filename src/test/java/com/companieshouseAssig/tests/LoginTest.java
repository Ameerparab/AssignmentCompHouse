package com.companieshouseAssig.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;

public class LoginTest extends BaseClass {

	@Test(priority = 1)
	public void adminClick() {
		String adminClk = homePage.adminClick();
		Assert.assertTrue(adminClk.contains("admin"));
	}

	@Test(priority = 2)
	public void adminLogin() {
		String adminLoginMessage = loginPage.adminLoginMessage();
		Assert.assertTrue(adminLoginMessage.contains("Log into your"));
	}

	@Test(priority = 3)
	public void appLoginTest() {
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		loginPage.doLogin(username, password);
		String logOutOpt = adminPage.logOutOption();
		Assert.assertTrue(logOutOpt.contains("Logout"));
	}
}
