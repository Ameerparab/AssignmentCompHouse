package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class CreateRoomTest extends BaseClass {

	@Test(priority = 1)
	public void login() throws InterruptedException {
		homePage.adminClick();
		loginPage.adminLoginMessage();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		Thread.sleep(2000);
		loginPage.doLogin(username, password);
	}

	@Test(priority = 2)
	public void sendbookDetails() throws InterruptedException {
		String room = prop.getProperty("room").trim();
		String price = prop.getProperty("price").trim();
		adminPage.createRoom(room, price);
	}

}