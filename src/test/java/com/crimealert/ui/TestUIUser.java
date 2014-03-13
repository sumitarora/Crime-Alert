package com.crimealert.ui;

import static org.junit.Assert.assertEquals;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crimealert.init.BaseUITestCase;

@Slf4j
public class TestUIUser extends BaseUITestCase {
	
	@Test
	public void testUpdateProfile() throws InterruptedException {
		doLogin();
		
		driver.get(PROFILE);
		log.debug(driver.getCurrentUrl());
		
		WebElement fname = driver.findElement(By.id("fname"));
		fname.sendKeys("John");
		Thread.sleep(SLEEP);
		
		WebElement lname = driver.findElement(By.id("lname"));
		lname.sendKeys("Doe");
		Thread.sleep(SLEEP);
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("admin@gmail.com");
		Thread.sleep(SLEEP);
		
		WebElement about = driver.findElement(By.id("about"));
		about.sendKeys("About User");
		Thread.sleep(SLEEP);
		
		WebElement address = driver.findElement(By.id("address"));
		address.sendKeys("Address");
		Thread.sleep(SLEEP);
		
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys("City");
		Thread.sleep(SLEEP);
		
		WebElement state = driver.findElement(By.id("state"));
		state.sendKeys("State");
		Thread.sleep(SLEEP);
		
		WebElement country = driver.findElement(By.id("country"));
		country.sendKeys("Country");
		Thread.sleep(SLEEP);

		WebElement btnSubmit = driver.findElement(By.id("btnSuccess"));
		btnSubmit.click();
		Thread.sleep(SLEEP);
		
		final String currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("users link not working", currentUrl,USERS + "/");		
	}
	
	
	@Test
	public void testUserCreate() throws InterruptedException {
		doLogin();
		
		driver.get(USER_CREATE);
		
		WebElement fname = driver.findElement(By.id("fname"));
		fname.sendKeys("John");
		Thread.sleep(SLEEP);
		
		WebElement lname = driver.findElement(By.id("lname"));
		lname.sendKeys("Doe");
		Thread.sleep(SLEEP);
		
		WebElement email = driver.findElement(By.id("email"));
		email.sendKeys("john@gmail.com");
		Thread.sleep(SLEEP);
		
		WebElement about = driver.findElement(By.id("about"));
		about.sendKeys("About User");
		Thread.sleep(SLEEP);
		
		WebElement address = driver.findElement(By.id("address"));
		address.sendKeys("Address");
		Thread.sleep(SLEEP);
		
		WebElement city = driver.findElement(By.id("city"));
		city.sendKeys("City");
		Thread.sleep(SLEEP);
		
		WebElement state = driver.findElement(By.id("state"));
		state.sendKeys("State");
		Thread.sleep(SLEEP);
		
		WebElement country = driver.findElement(By.id("country"));
		country.sendKeys("Country");
		Thread.sleep(SLEEP);

		WebElement btnSubmit = driver.findElement(By.id("btnSuccess"));
		btnSubmit.click();
		Thread.sleep(SLEEP);	
		
		final String currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("crime link not working", currentUrl,USERS);		
	}

}
