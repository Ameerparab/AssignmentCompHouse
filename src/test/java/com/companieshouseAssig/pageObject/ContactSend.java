package com.companieshouseAssig.pageObject;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ContactSend {

    private Page page;

    // 1. String Locators
    private String contNameTxt = "//input[@id='name']";
    private String contactEmailTxt = "//input[@id='email']";
    private String contPhoneTxt = "//input[@id='phone']";
    private String subjectTxt = "//input[@id='subject']";
    private String descriptTxt = "//textarea[@id='description']";
    private String submitContButton = "//button[@id='submitContact']";
    private String thanksMessage = "//h2[contains(normalize-space(), 'Thanks for getting in touch')]";
    private String dbCheck = "//i[@class='fa fa-inbox']";
  
    // 2. page constructor:
    public ContactSend(Page page) {
        this.page = page;
    }

    // 3. page actions/methods:
	public void submitContactDetails(String cntName, String cntEmail, String cntPhone, String cntSubject,
			String cntMessage) {
		    page.fill(contNameTxt, cntName);
		    page.fill(contactEmailTxt, cntEmail);
		    page.fill(contPhoneTxt, cntPhone);
		    page.fill(subjectTxt, cntSubject);
		    page.fill(descriptTxt, cntMessage);
		    page.click(submitContButton);
	}
	
	public String verfiyThanksMessage() {
		Locator thanksMes = page.locator(thanksMessage);
		String thanksMessg = thanksMes.textContent().trim();
        return thanksMessg;
	}
	
	public boolean verfiyDataEntryMessage(String cntName, String cntSubject) throws InterruptedException {
	page.click(dbCheck);
    page.waitForSelector("div.messages > div.row.detail");
    Locator rows = page.locator("div.messages > div.row.detail");
    for (int i = 0; i < rows.count(); i++) {
        Locator currentRow = rows.nth(i);
        String name = currentRow.locator("div.col-sm-2 > p").textContent().trim();
        String subject = currentRow.locator("div.col-sm-9 > p").textContent().trim();
        if (name.equals(cntName) && subject.equals(cntSubject) ) {
            System.out.println("Match found - Name: " + name + ", Subject: " + subject);
            return true; 
        }
    }
    return false; 
}
}