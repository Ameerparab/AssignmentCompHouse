package com.companieshouseAssig.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;

public class NFTScalabilityLoginTest extends BaseClass {

	@Test(priority = 1)
	public void scaleLoginwithMultipleUserTest() {
		String url = prop.getProperty("url").trim();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		String mrdLog = prop.getProperty("moderateLogin").trim();
		loginPage.scaleLogin(username, password, url, mrdLog);
		String logOutOpt = adminPage.logOutOption();
		Assert.assertTrue(logOutOpt.contains("Logout"));
	}

	@Test(priority = 2)
	public void scaleLoginTestwithMultipleattempts() throws InterruptedException {
		String url = prop.getProperty("url").trim();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		String attempt = prop.getProperty("noofattempts").trim();
		boolean result = loginPage.scaleloginmultipleattempt(username, password, url, attempt);
		Assert.assertTrue(result, "The longin with " + attempt + " attempts was succsuffly");

	}

	@Test(priority = 3)
	public void scaleLoginTestwithWithLargeNumberOfUsers() {
		String url = prop.getProperty("url").trim();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		String maxLog = prop.getProperty("maxLogin").trim();
		loginPage.scaleLogin(username, password, url, maxLog);
		String logOutOpt = adminPage.logOutOption();
		Assert.assertTrue(logOutOpt.contains("Logout"));
	}
}
