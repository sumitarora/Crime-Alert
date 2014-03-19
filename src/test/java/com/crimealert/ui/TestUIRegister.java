package com.crimealert.ui;

import static org.junit.Assert.assertEquals;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crimealert.init.BaseUITestCase;

@Slf4j
public class TestUIRegister extends BaseUITestCase {

	@Test
	public void testRegisterSuccess() throws InterruptedException {
		driver.get(REGISTER_URL);
		
		WebElement fname = driver.findElement(By.id("first_name"));
		fname.sendKeys("John");
		Thread.sleep(SLEEP);
		
		WebElement lname = driver.findElement(By.id("last_name"));
		lname.sendKeys("Doe");
		Thread.sleep(SLEEP);
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("johngmail.com");
		Thread.sleep(SLEEP);
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("123456");
		Thread.sleep(SLEEP);
		
		WebElement cpassword = driver.findElement(By.id("password_confirmation"));
		cpassword.sendKeys("123456");
		Thread.sleep(SLEEP);

		WebElement btnSubmit = driver.findElement(By.id("btnRegister"));
		btnSubmit.click();
		Thread.sleep(SLEEP);
		
		log.debug(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(),LOGIN_URL);			
	}
}
