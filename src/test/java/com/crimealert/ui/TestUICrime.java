package com.crimealert.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import lombok.extern.slf4j.Slf4j;

import com.crimealert.init.BaseUITestCase;

@Slf4j
public class TestUICrime extends BaseUITestCase {

	@Test
	public void testCreateCrime() throws InterruptedException {
		doLogin();
		driver.get(CRIME_CREATE);
		
		WebElement title = driver.findElement(By.id("title"));
		title.sendKeys("Crime Title");
		Thread.sleep(SLEEP);
		
		WebElement description = driver.findElement(By.id("description"));
		description.sendKeys("Crime Description");
		Thread.sleep(SLEEP);
		
		WebElement btnSubmit = driver.findElement(By.id("btnSuccess"));
		btnSubmit.click();
		Thread.sleep(SLEEP);
		
		log.debug(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(),CRIME_LIST);			
	}
}
