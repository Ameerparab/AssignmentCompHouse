package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class CheckingLinksTest extends BaseClass {

	@Test(priority = 1)
	public void gitHubRestLink() {
		String[] githubrespon = homePage.clickinGithubLinks();
		Assert.assertTrue(githubrespon[0].contains("GitHub"));
		Assert.assertTrue(githubrespon[1].contains("booker-platform"));
	}

	@Test(priority = 2)
	public void pipelineLink() throws InterruptedException {
		String pipelineTitle = homePage.clickinPipelineLinks();
		Assert.assertTrue(pipelineTitle.contains("booker-platform"));
	}

	@Test(priority = 3)
	public void gitHubFeatureLink() throws InterruptedException {
		String githubfeaturee = homePage.gitHubFeature();
		Assert.assertTrue(githubfeaturee.contains("feature"));
	}
}
