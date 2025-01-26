package com.companieshouseAssig.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import com.companieshouseAssig.utilities.ExcelReader;

public class LoginTestDataDriven extends BaseClass {

	// DataProvider to fetch test data from Excel for login tests.
	@DataProvider(name = "loginTestData")
	public Object[][] getLoginTestData() {
		String excelPath = "./src/test/resources/TestData.xlsx";
		String sheetName = "Login";
		return ExcelReader.getExcelData(excelPath, sheetName);
	}

	// Test to verify application login functionality using data-driven testing.
	@Test(dataProvider = "loginTestData", priority = 1, description = "Verify login functionality")
	public void verifyAppLoginFunctionality(String username, String password, String expectedOutcome)
			throws InterruptedException {
		homePage.adminClick();
		loginPage.adminLoginMessage();
		loginPage.doLogin(username, password);
		if (expectedOutcome.equalsIgnoreCase("Success")) {
			System.out.println("This is success before logout");
			String logOutOpt = adminPage.logOutOption();
			Assert.assertTrue(logOutOpt.contains("Logout"), "Logout option not found after attempt ");
			String logOut = adminPage.logOutAdmin();
			Assert.assertTrue(logOut.contains("Log into your"));
		} else if (expectedOutcome.equalsIgnoreCase("Failure")) {
			String errorMessage = loginPage.getErrorMessage();
			Assert.assertTrue(errorMessage.contains("Invalid credentials"),
					"Expected error message not found for invalid login attempt.");
		} else {
			Assert.fail("Invalid expected outcome specified in test data: " + expectedOutcome);
		}
	}
}
