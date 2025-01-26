package com.companieshouseAssig.pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.*;

import java.util.Random;

public class BookRoom {

	private Page page;

	// 1. String Locators
	private String roomVerify = "//h2[normalize-space()='Rooms']";
	private String firstNameTxt = "//input[@placeholder='Firstname']";
	private String lastNameTxt = "//input[@placeholder='Lastname']";
	private String emailTxt = "//input[@name='email']";
	private String phoneTxt = "//input[@name='phone']";
	private String todayButton = "//button[normalize-space()='Today']";
	private String backButton = "//button[normalize-space()='Back']";
	private String nextButton = "//button[normalize-space()='Next']";
	private String bookThisRoom = "//div[4]//div[1]//div[1]//div[3]//button[1]";
	private String bookButton = "//button[normalize-space()='Book']";
	private String cancelbutton = "//button[normalize-space()='Cancel']";
	private String bookingSuccess = "//h3[normalize-space()='Booking Successful!']";
	private String close = "//button[normalize-space()='Close']";

	// 2. page constructor:
	public BookRoom(Page page) {
		this.page = page;
	}

	// 3. page actions/methods
	public String[] bookingRoom(String cntFirstName, String cntLastName, String cntEmail, String cntPhone)
			throws InterruptedException {
		page.click(bookThisRoom);
		int randomNum = new Random().nextInt(15) + 3;
		for (int i = 0; i <= randomNum; i++) {
			page.click(nextButton);
			i++;
		}
		Thread.sleep(2000);
		String bookstdr = String.format("%02d", new Random().nextInt(29) + 1);
		String bookenddr = String.format("%02d", Integer.parseInt(bookstdr) + 1);
		System.out.println("Start Date: " + bookstdr + ", End Date: " + bookenddr);
		Locator startDate = page.locator("//button[normalize-space()='" + bookstdr + "'][1]");
		Locator endDate = page.locator("//button[normalize-space()='" + bookenddr + "'][1]");
		BoundingBox startBox = startDate.boundingBox();
		BoundingBox endBox = endDate.boundingBox();
		page.mouse().move(startBox.x + startBox.width / 2, startBox.y + startBox.height / 2);
		page.mouse().down();
		page.mouse().move(endBox.x + endBox.width / 2, endBox.y + endBox.height / 2,
				new Mouse.MoveOptions().setSteps(10));
		page.mouse().up();
		Thread.sleep(3000);
		page.fill(firstNameTxt, cntFirstName);
		page.fill(lastNameTxt, cntLastName);
		page.fill(emailTxt, cntEmail);
		page.fill(phoneTxt, cntPhone);
		page.click(bookButton);
		Thread.sleep(2000);
		return new String[] { bookingSuccess, close };
	}

	public void closeBookMessage() throws InterruptedException {
		page.click(close);
	}
}