package com.companieshouseAssig.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;

public class NFTHomePageStressTest extends BaseClass {

	@Test(priority = 1)

	public void checkStressTest() throws InterruptedException {
		String url = prop.getProperty("url").trim();
		String stresstestno = prop.getProperty("stresstestno").trim();
		boolean stressTestResult = homePage.stresTest(url, stresstestno);
		Assert.assertTrue(stressTestResult, "Stress test failed: Not all users completed successfully.");
		System.out.println("Stress test completed successfully.");
	}
}
