package com.crimealert.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import lombok.extern.slf4j.Slf4j;

import com.crimealert.init.BaseUITestCase;

@Slf4j
public class TestUIComment extends BaseUITestCase {

	@Test
	public void testCrimeComment() throws InterruptedException {
		doLogin();
		driver.get(BASE_URL);

		WebElement btnCrimeDetails = driver.findElement(By.id("btnCrimeDetails"));
		String href= btnCrimeDetails.getAttribute("href");
		log.debug(href);
		String ar[] = href.split("/");
		int crimeId = Integer.parseInt(ar[ar.length - 1]);
		log.debug("crime if: {}", crimeId);
		btnCrimeDetails.click();
		Thread.sleep(SLEEP);
		assertEquals(driver.getCurrentUrl(), href);
		
		int initalCommentCount = driver.findElements(By.id("comments")).size();
		log.debug("inital count: {}", initalCommentCount);
		
		WebElement txtComment = driver.findElement(By.id("txtComment"));
		txtComment.sendKeys("User Comment");
		Thread.sleep(SLEEP);

		WebElement btnAddComment = driver.findElement(By.id("btnAddComment"));
		btnAddComment.click();
		Thread.sleep(SLEEP);
		
		int finalCommentCount = driver.findElements(By.id("comments")).size();
		log.debug("final count: {}", finalCommentCount);
		
		assertEquals(initalCommentCount, finalCommentCount - 1);		
		
	}
	
	
	@Test
	public void testComplaintComment() throws InterruptedException {
		doLogin();
		driver.get(BASE_URL);

		WebElement btnComplaintDetails = driver.findElement(By.id("btnComplaintDetails"));
		String href= btnComplaintDetails.getAttribute("href");
		log.debug(href);
		String ar[] = href.split("/");
		int complaintId = Integer.parseInt(ar[ar.length - 1]);
		log.debug("crime if: {}", complaintId);
		btnComplaintDetails.click();
		Thread.sleep(SLEEP);
		assertEquals(driver.getCurrentUrl(), href);
		
		int initalCommentCount = driver.findElements(By.id("comments")).size();
		log.debug("inital count: {}", initalCommentCount);
		
		WebElement txtComment = driver.findElement(By.id("txtComment"));
		txtComment.sendKeys("User Comment");
		Thread.sleep(SLEEP);

		WebElement btnAddComment = driver.findElement(By.id("btnAddComment"));
		btnAddComment.click();
		Thread.sleep(SLEEP);
		
		int finalCommentCount = driver.findElements(By.id("comments")).size();
		log.debug("final count: {}", finalCommentCount);
		
		assertEquals(initalCommentCount, finalCommentCount - 1);		
		
	}
}
