package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class HomePageTest extends BaseClass {

	@Test(priority = 1)
	public void homePageTitleTest() {
		String title = homePage.getHomePageTitle();
		System.out.println("Page title is: " + title);
		Assert.assertTrue(title.contains("Restful-booker-platform demo"));
	}

	@Test(priority = 2)
	public void homePageURLTest() {
		String url = homePage.getHomePageURL();
		System.out.println("Page URL is: " + url);
		Assert.assertTrue(url.contains("automationintesting"));
	}
}