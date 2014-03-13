package com.crimealert.ui;

import static org.junit.Assert.assertEquals;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.crimealert.init.BaseUITestCase;

@Slf4j
public class TestUINavigation extends BaseUITestCase {
	
	@Test
	public void testNavigation() throws InterruptedException {
		doLogin();
		driver.get(HOME_URL);
		log.debug("starting navigation");
		
		WebElement btnComplaint = driver.findElement(By.id("btnComplaint"));
		btnComplaint.click();
		String currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		log.debug(COMPLAINT_LIST);
		assertEquals("complaint link not working", currentUrl,COMPLAINT_LIST);
		Thread.sleep(SLEEP);		

		WebElement btnCrime = driver.findElement(By.id("btnCrime"));
		btnCrime.click();
		Thread.sleep(SLEEP);
		currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("crime link not working", currentUrl,CRIME_LIST);
		Thread.sleep(SLEEP);		
		
		WebElement btnProfile = driver.findElement(By.id("btnProfile"));
		btnProfile.click();
		currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("profile link not working", currentUrl,PROFILE);
		Thread.sleep(SLEEP);				
		
		WebElement btnUsers = driver.findElement(By.id("btnUsers"));
		btnUsers.click();
		currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("users link not working", currentUrl,USERS);
		Thread.sleep(SLEEP);

		WebElement btnHome = driver.findElement(By.id("btnHomeHeader"));
		btnHome.click();
		currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("home link not working", currentUrl,HOME_URL);
		Thread.sleep(SLEEP);
		
		WebElement btnLogout = driver.findElement(By.id("btnLogout"));
		btnLogout.click();
		currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("logout link not working", currentUrl,LOGIN_URL);
		Thread.sleep(SLEEP);
	}
	
	@Test
	public void testCrimeSearch() throws InterruptedException {
		driver.get(BASE_URL);
		
		WebElement criteria = driver.findElement(By.id("criteria"));
		criteria.sendKeys("sa");
		Thread.sleep(SLEEP);		

		WebElement btnSearch = driver.findElement(By.id("btnSearch"));
		btnSearch.click();
		Thread.sleep(SLEEP);
		final String currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("crime link not working", currentUrl,CRIME_SEARCH);
		Thread.sleep(SLEEP);				
		
	}
	
	@Test
	public void testComplaintSearch() throws InterruptedException {
		driver.get(BASE_URL);
		
		WebElement criteria = driver.findElement(By.id("criteria"));
		criteria.sendKeys("sa");
		Thread.sleep(SLEEP);
		
		Select type = new Select(driver.findElement(By.id("searchType")));
		type.selectByIndex(1);
		Thread.sleep(SLEEP);

		WebElement btnSearch = driver.findElement(By.id("btnSearch"));
		btnSearch.click();
		Thread.sleep(SLEEP);
		final String currentUrl = driver.getCurrentUrl();
		log.debug(currentUrl);
		assertEquals("crime link not working", currentUrl,COMPLAINT_SEARCH);
		Thread.sleep(SLEEP);				
		
	}

}
