package com.crimealert.ui;


import static org.junit.Assert.assertEquals;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crimealert.init.BaseUITestCase;

@Slf4j
public class TestUILogin extends BaseUITestCase {

	@Test 
	public void testLoginSuccess() throws InterruptedException {
		driver.get(LOGIN_URL);
		
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("admin@gmail.com");
		Thread.sleep(SLEEP);
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("123456");
		Thread.sleep(SLEEP);
		
		WebElement btnSubmit = driver.findElement(By.id("btnSubmit"));
		btnSubmit.click();
		Thread.sleep(SLEEP);
		
		log.debug(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(),BASE_URL);		
	}
	
	@Test 
	public void testLoginFailure() throws InterruptedException {
		driver.get(LOGIN_URL);
		
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("sumit@gmail.com");
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("12345678");

		WebElement btnSubmit = driver.findElement(By.id("btnSubmit"));
		btnSubmit.click();
		Thread.sleep(SLEEP);
		
		log.debug(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(),LOGIN_FAILURE_URL);		
	}
}
