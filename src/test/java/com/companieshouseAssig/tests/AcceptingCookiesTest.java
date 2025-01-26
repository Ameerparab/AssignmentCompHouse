package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class AcceptingCookiesTest extends BaseClass {

	@Test(priority = 1)
	public void homePageTitleTest() {
		boolean isLocatorMissing = homePage.acceptCookies();
		Assert.assertTrue(isLocatorMissing, "Locator is missing and Accepted ");
	}
}