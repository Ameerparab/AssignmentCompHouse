package com.companieshouseAssig.pageObject;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import com.companieshouseAssig.base.BaseClass;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BaseClass {

	private Page page;
	private AdminPage adminPage;

	// 1. String Locators
	private String username = "//input[@id='username']";
	private String password = "//input[@id='password']";
	private String loginBtn = "//button[@id='doLogin']";
	private String pageAdminLink = "//a[normalize-space()='admin panel']";
	private String loginmessage = "//h2[normalize-space()='Log into your account']";
    private String errorMessage   = "errorMessage";  //no error message is displaying in 
	
	// 2. page constructor:
	public LoginPage(Page page) {
		this.page = page;
		this.adminPage = new AdminPage(page);

	}

	// 3. page actions/methods:
	public String adminLoginMessage() {
		return loginmessage;
	}

    public String getErrorMessage() {
        return page.locator("errorMessage").textContent(); 
    }
	
	public void doLogin(String appUserName, String appPassword) {
		page.fill(username, appUserName);
		page.fill(password, appPassword);
		page.click(loginBtn);
	}

	public void scaleLogin(String appUName, String appPword, String url, String modertLog) {
		List<BrowserContext> contexts = new ArrayList<>();
		List<Page> pages = new ArrayList<>();
		int moderateLog = Integer.parseInt(modertLog);
		for (int i = 0; i < moderateLog; i++) {
			BrowserContext userContext = page.context().browser().newContext();
			Page userPage = userContext.newPage();
			contexts.add(userContext);
			pages.add(userPage);
		}
		// Perform login for each user
		for (Page userPage : pages) {
			userPage.navigate(url);
			Locator adminLink = userPage.locator(pageAdminLink);
			adminLink.click();
			userPage.fill(username, appUName);
			userPage.fill(password, appPword);
			userPage.click(loginBtn);
		}
		for (BrowserContext userContext : contexts) {
			userContext.close();
		}
	}

	public boolean scaleloginmultipleattempt(String appUName, String appPword, String url, String attempt) throws InterruptedException {
		int numberOfattempts = Integer.parseInt(attempt);
		Locator adminLink = page.locator(pageAdminLink);
		adminLink.click();
		boolean result = true;
		for (int i = 0; i < numberOfattempts; i++) {
			try {
				page.fill(username, appUName);
				page.fill(password, appPword);
				page.click(loginBtn);
				String logOutOpt = adminPage.logOutOption();
				Assert.assertTrue(logOutOpt.contains("Logout"), "Logout option not found after attempt " + (i + 1));
				String logOut = adminPage.logOutAdmin();
				Assert.assertTrue(logOut.contains("Log into your"),
						"Login page not displayed after logout in attempt " + (i + 1));
			} catch (AssertionError e) {
				System.err.println("Assertion failed in attempt " + (i + 1) + ": " + e.getMessage());
				result = false;
			}
		}
		return result;
	}

	public void scaleLoginTestwithWithLargeNumberOfUsers(String appUName, String appPword, String url,
			String maxlogin) {
		int maxloginno = Integer.parseInt(maxlogin);
		List<BrowserContext> contexts = new ArrayList<>();
		List<Page> pages = new ArrayList<>();
		for (int i = 0; i < maxloginno; i++) {
			BrowserContext userContext = page.context().browser().newContext();
			Page userPage = userContext.newPage();
			contexts.add(userContext);
			pages.add(userPage);
		}
		for (Page userPage : pages) {
			userPage.navigate(url);
			Locator adminLink = userPage.locator(pageAdminLink);
			adminLink.click();
			userPage.fill(username, appUName);
			userPage.fill(password, appPword);
			userPage.click(loginBtn);
		}
		for (BrowserContext userContext : contexts) {
			userContext.close();
		}
	}

}