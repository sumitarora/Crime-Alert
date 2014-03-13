package com.crimealert.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crimealert.init.BaseUITestCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUIComplaint extends BaseUITestCase {

	@Test
	public void testCreateComplaint() throws InterruptedException {
		doLogin();
		driver.get(COMPLAINT_CREATE);
		
		WebElement title = driver.findElement(By.id("Title"));
		title.sendKeys("Complaint Title");
		Thread.sleep(SLEEP);
		
		WebElement description = driver.findElement(By.id("Description"));
		description.sendKeys("Complaint Description");
		Thread.sleep(SLEEP);
		

		WebElement btnSubmit = driver.findElement(By.id("btnSuccess"));
		btnSubmit.click();
		Thread.sleep(SLEEP);
		
		log.debug(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(),COMPLAINT_LIST);		
	}
	
}
