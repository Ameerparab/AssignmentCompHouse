package com.companieshouseAssig.tests;

import com.companieshouseAssig.base.BaseClass;
import com.companieshouseAssig.utilities.ExtentReportListener;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class NFTPerformanceLoginTest extends BaseClass {

	@Test(priority = 1)
	public void LoginperformaneLoadTime() {
		homePage.adminClick();
		loginPage.adminLoginMessage();
		String username = prop.getProperty("username").trim();
		String password = prop.getProperty("password").trim();
		long startTime = System.currentTimeMillis();
		loginPage.doLogin(username, password);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("pageLoadTime", loadTime);
		ExtentReportListener.logNonFunctionalMetrics("Login Performance Time", loadTime, "ms");
		System.out.println("Login Performance Time" + loadTime + "ms");
		Assert.assertTrue(loadTime < 5000, "Login page load time should be less than 5 seconds");
	}

}