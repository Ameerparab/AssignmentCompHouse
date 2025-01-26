package com.companieshouseAssig.base;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Page;
import com.companieshouseAssig.factory.PlaywrightFactory;
import com.companieshouseAssig.pageObject.AdminPage;
import com.companieshouseAssig.pageObject.BookRoom;
import com.companieshouseAssig.pageObject.ContactSend;
import com.companieshouseAssig.pageObject.HomePage;
import com.companieshouseAssig.pageObject.LoginPage;

public class BaseClass {

	protected PlaywrightFactory pf;
	protected Page page;
	protected Properties prop;

	protected HomePage homePage;
	protected LoginPage loginPage;
	protected AdminPage adminPage;
	protected ContactSend contactSend;
	protected BookRoom bookRoom;

	@BeforeClass
	public void setup() {
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
		loginPage = new LoginPage(page);
		adminPage = new AdminPage(page);
		contactSend = new ContactSend(page);
		bookRoom = new BookRoom(page);
		String message = homePage.getHomeWelcome();
		assert message.contains("Welcome to Restful");

	}

	@AfterClass
	public void tearDown() {
		if (page != null) {
			page.context().browser().close();
		}
	}
}