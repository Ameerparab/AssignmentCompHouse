package com.companieshouseAssig.tests;

import com.companieshouseAssig.base.BaseClass;
import com.companieshouseAssig.utilities.ExtentReportListener;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class NFTPerformanceHomePageTest extends BaseClass {

	@Test(priority = 1)
	public void HomePagePerformanceLoadTime() {
		String url = prop.getProperty("url").trim();
		long startTime = System.currentTimeMillis();
		homePage.navigateToHomePage(url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("pageLoadTime", loadTime);
		ExtentReportListener.logNonFunctionalMetrics("Home Page Load Time", loadTime, "ms");
		System.out.println("Home Page Load Time" + loadTime + "ms");
		Assert.assertTrue(loadTime < 5000, "Home page load time should be less than 5 seconds");
	}

}