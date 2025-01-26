package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class BookRoomAdminTest extends BaseClass {

	@Test(priority = 1)
	public void sendbookDetails() throws InterruptedException {
		homePage.adminClick();
		loginPage.adminLoginMessage();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		loginPage.doLogin(username, password);
		String cntFirstName = prop.getProperty("name").trim();
		String cntLastName = prop.getProperty("lastName").trim();
		String adminBookConfirm = adminPage.reportMangt(cntFirstName, cntLastName);
		Assert.assertTrue(adminBookConfirm.contains("Logout"));
	}
}