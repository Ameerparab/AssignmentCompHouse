package com.companieshouseAssig.pageObject;

import java.util.Random;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;

public class AdminPage {

	private Page page;

	// 1. String Locators
	private String logoutButton = "//a[normalize-space()='Logout']";
	private String roomNo = "//input[@id='roomName']";
	private String typeDropDown = "//select[@id='type']";
	private String roomPrice = "//input[@id='roomPrice']";
	private String accessible = "//select[@id='accessible']";
	private String wifiCheck = "//input[@id='wifiCheckbox']";
	private String tvCheck = "//input[@id='tvCheckbox']";
	private String radioCheck = "//input[@id='radioCheckbox']";
	private String refreshCheck = "//input[@id='refreshCheckbox']";
	private String safeCheck = "//input[@id='safeCheckbox']";
	private String viewCheck = "//input[@id='viewsCheckbox']";
	private String createButton = "//button[@id='createRoom']";
	private String loginmessage = "//h2[normalize-space()='Log into your account']";
	private String reportLink = "//a[@id='reportLink']";
	private String nextButon = "//button[normalize-space()='Next']";
	private String roomDropDown = "//select[@id='roomid']";
	private String depositDropDown = "//select[@id='depositpaid']";
	private String firstName = "//input[@placeholder='Firstname']";
	private String LastName = "//input[@placeholder='Lastname']";
	private String bookButn = "//button[normalize-space()='Book']";
	private String dateCellLocator = "//div[@class='rbc-date-cell']//button[@role='cell'][normalize-space()='%s']";

	// 2. page constructor:
	public AdminPage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String logOutOption() {
		return logoutButton;
	}

	public String logOutAdmin() throws InterruptedException {
		page.click(logoutButton);
		return loginmessage;
	}

	public void createRoom(String room, String price) throws InterruptedException {
		page.fill(roomNo, room);
		page.selectOption(typeDropDown, "Family");
		page.selectOption(accessible, "true");
		page.fill(roomPrice, price);
		page.check(wifiCheck);
		page.check(tvCheck);
		page.check(radioCheck);
		page.check(refreshCheck);
		page.check(safeCheck);
		page.check(viewCheck);
		page.click(createButton);
	}

	public String reportMangt(String cntFirstName, String cntLastName) throws InterruptedException {
		page.click(reportLink);
		int randomNum = new Random().nextInt(15) + 3;
		for (int i = 0; i < randomNum; i++) {
			if (page.locator(nextButon).isVisible()) {
				page.click(nextButon);
				page.waitForTimeout(500);
			}
		}
		// Generate start and end dates
		int startDay = new Random().nextInt(27) + 1;
		int endDay = Math.min(startDay + 2, 30);
		String startDateStr = String.format("%02d", startDay);
		String endDateStr = String.format("%02d", endDay);
		System.out.println("Start Date Locator: " + String.format(dateCellLocator, startDateStr));
		System.out.println("End Date Locator: " + String.format(dateCellLocator, endDateStr));
		Locator startDate = page.locator(String.format(dateCellLocator, startDateStr));
		Locator endDate = page.locator(String.format(dateCellLocator, endDateStr));
		startDate.waitFor();
		endDate.waitFor();
		if (startDate.isVisible() && endDate.isVisible()) {
			BoundingBox startBox = startDate.boundingBox();
			BoundingBox endBox = endDate.boundingBox();

			page.mouse().move(startBox.x + startBox.width / 2, startBox.y + startBox.height / 2);
			page.mouse().down();
			page.waitForTimeout(500);
			page.mouse().move(endBox.x + endBox.width / 2, endBox.y + endBox.height / 2,
					new Mouse.MoveOptions().setSteps(10));
			page.mouse().up();
		} else {
			throw new IllegalStateException("Start or End Date not visible. Verify locators or data.");
		}
		Thread.sleep(3000);
		page.fill(firstName, cntFirstName);
		page.fill(LastName, cntLastName);
		Thread.sleep(500);
		page.selectOption(roomDropDown, "1");
		page.selectOption(depositDropDown, "false");
		page.click(bookButn);
		page.waitForTimeout(2000);
		return logoutButton;
	}
}
