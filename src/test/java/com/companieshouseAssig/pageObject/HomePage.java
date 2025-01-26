package com.companieshouseAssig.pageObject;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.companieshouseAssig.base.BaseClass;
import com.companieshouseAssig.factory.PlaywrightFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HomePage extends BaseClass {

	private Page page;
	// 1. String Locators
	private String pageHomeLink = "//a[normalize-space()='home page']";
	private String pageAdminLink = "//a[normalize-space()='admin panel']";
	private String pageRestBookerLink = "//a[normalize-space()='restful-booker-platform source code']";
	private String pageBuildLink = "//a[normalize-space()='build process in this public build pipeline']";
	private String pageRestBookerSorcLink = "//a[normalize-space()='restful-booker-platform source']";
	private String pageMoreFeaturLink = "//a[normalize-space()='read more about the features here']";
	private String pageraiseDefectLink = "//a[normalize-space()='feel free to raise it here']";
	private String buttonLetMeHack = "//button[normalize-space()='Let me hack!']";
	private String welcomMessage = "//h1[normalize-space()='Welcome to Restful Booker Platform']";
	private String gitHubrest = "//a[normalize-space()='restful-booker-platform']";
	private String pipleineCric = "//h1[@class='css-gwiobt']//span[contains(text(),'restful-booker-platform')]";

	// 2. page constructor:
	public HomePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getHomeWelcome() {
		Locator welcomMess = page.locator(welcomMessage);
		String welcomeText = welcomMess.textContent();
		return welcomeText;
	}

	public String adminClick() {
		Locator adminLink = page.locator(pageAdminLink);
		String adminLk = adminLink.textContent();
		adminLink.click();
		return adminLk;
	}

	public String homeClick() {
		Locator homeLink = page.locator(pageHomeLink);
		String homeiLnk = homeLink.textContent();
		homeLink.click();
		return homeiLnk;
	}

	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url = page.url();
		System.out.println("page url : " + url);
		return url;
	}

	public boolean acceptCookies() {
		page.click(buttonLetMeHack);
		Locator letmehack = page.locator(buttonLetMeHack);
		boolean isLocatorMissing = letmehack.isVisible();
		return !isLocatorMissing;
	}

	public String[] clickinGithubLinks() {
		page.click(pageRestBookerLink);
		String resttitle = page.title();
		return new String[] { resttitle, gitHubrest };
	}

	public String clickinPipelineLinks() throws InterruptedException {
		page.goBack();
		Thread.sleep(2000);
		page.click(pageBuildLink);
		Thread.sleep(10000);
		return pipleineCric;
	}

	public String gitHubFeature() throws InterruptedException {
		page.goBack();
		page.click(pageMoreFeaturLink);
		Thread.sleep(2000);
		return gitHubrest;
	}

	public void navigateToHomePage(String url) {
		page.navigate(url);
	}

	public void concurrentUser(String url, String concrtUser, String browser) throws InterruptedException {
		int numberOfUsers = Integer.parseInt(concrtUser);
		ExecutorService executor = Executors.newFixedThreadPool(numberOfUsers);
		for (int i = 0; i < numberOfUsers; i++) {
			executor.execute(() -> {
				try {
					// Create a new Playwright instance for each thread
					Playwright threadPlaywright = Playwright.create();
					PlaywrightFactory.setPlaywright(threadPlaywright);
					Browser threadBrowser = threadPlaywright.chromium()
							.launch(new BrowserType.LaunchOptions().setHeadless(false));
					PlaywrightFactory.setBrowser(threadBrowser);
					BrowserContext threadContext = threadBrowser.newContext();
					PlaywrightFactory.setBrowserContext(threadContext);
					Page threadPage = threadContext.newPage();
					PlaywrightFactory.setPage(threadPage);
					threadPage.navigate(url);
					System.out.println("User " + Thread.currentThread().threadId() + " loaded the page.");
					threadPage.close();
					threadContext.close();
					threadBrowser.close();
					threadPlaywright.close();
				} catch (Exception e) {
					System.err.println("Error in thread " + Thread.currentThread().threadId() + ": " + e.getMessage());
				}
			});
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.MINUTES);
	}

	public boolean stresTest(String url, String stresstestno) throws InterruptedException {
		int numberOfUsers = Integer.parseInt(stresstestno);
		ExecutorService executor = Executors.newFixedThreadPool(numberOfUsers);
		CountDownLatch latch = new CountDownLatch(numberOfUsers);
		AtomicBoolean allUsersSuccessful = new AtomicBoolean(true);
		for (int i = 0; i < numberOfUsers; i++) {
			executor.execute(() -> {
				try (Playwright playwright = Playwright.create()) {
					Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
					Page page = browser.newPage();
					page.navigate(url);
					System.out.println("User " + Thread.currentThread().threadId() + " loaded the page.");
				} catch (Exception e) {
					System.err.println("Error in User " + Thread.currentThread().threadId() + ": " + e.getMessage());
					allUsersSuccessful.set(false);
				} finally {
					latch.countDown();
				}
			});
		}
		boolean allThreadsCompleted = latch.await(1, TimeUnit.MINUTES);
		executor.shutdown();
		return allThreadsCompleted && allUsersSuccessful.get();
	}

}
