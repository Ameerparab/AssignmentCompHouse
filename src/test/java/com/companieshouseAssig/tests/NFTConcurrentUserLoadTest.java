package com.companieshouseAssig.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;

public class NFTConcurrentUserLoadTest extends BaseClass {

	@Test(priority = 1)

	public void concurrentUserLoadTest() throws InterruptedException {
		String url = prop.getProperty("url").trim();
		String concrtUser = prop.getProperty("concurtUser").trim();
		String browser = prop.getProperty("browser").trim();
		homePage.concurrentUser(url, concrtUser, browser);
		String logOutOpt = adminPage.logOutOption();
		Assert.assertTrue(logOutOpt.contains("Logout"));
	}
}
