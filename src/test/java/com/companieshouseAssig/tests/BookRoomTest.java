package com.companieshouseAssig.tests;

import org.testng.annotations.Test;
import com.companieshouseAssig.base.BaseClass;
import org.testng.Assert;

public class BookRoomTest extends BaseClass {
	@Test(priority = 1)
	public void sendbookDetails() throws InterruptedException {
		String cntFirstName = prop.getProperty("name").trim();
		String cntEmail = prop.getProperty("email").trim();
		String cntPhone = prop.getProperty("phone").trim();
		String cntLastName = prop.getProperty("lastName").trim();
		String[] BookingConfirm = bookRoom.bookingRoom(cntFirstName, cntLastName, cntEmail, cntPhone);
		Assert.assertTrue(BookingConfirm[0].contains("Booking Successful"));
		Assert.assertTrue(BookingConfirm[1].contains("Close"));
	}
	@Test(priority = 2)
	public void closeBookingMessage() throws InterruptedException {
	}
}