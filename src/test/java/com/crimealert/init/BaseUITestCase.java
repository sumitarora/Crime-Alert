package com.crimealert.init;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseUITestCase {
	
	//http://chromedriver.storage.googleapis.com/index.html?path=2.9/

	public WebDriver driver;	
	
	public static int SLEEP 				    = 1000;
	
	public static String BASE_URL 				= "http://localhost:8080/crime-alert/";
	public static String LOGIN_URL 				= BASE_URL + "login";
	public static String LOGIN_FAILURE_URL 		= BASE_URL + "login?error";
	public static String HOME_URL		 		= BASE_URL + "home";	
	public static String REGISTER_URL 			= BASE_URL + "register";
	
	public static String CRIME_CREATE 			= BASE_URL + "crime/create";
	public static String CRIME_LIST 			= BASE_URL + "crime/list";
	public static String CRIME_LIST_ALL 		= BASE_URL + "crime/list/all";
	public static String CRIME_VIEW				= BASE_URL + "crime/view/";
	
	public static String COMPLAINT_CREATE 		= BASE_URL + "complaint/create";
	public static String COMPLAINT_LIST 		= BASE_URL + "complaint/list";
	public static String COMPLAINT_LIST_ALL 	= BASE_URL + "complaint/list/all";
	
	public static String PROFILE	 			= BASE_URL + "profile";
	
	public static String USERS		 			= BASE_URL + "user";
	public static String USER_CREATE 			= BASE_URL + "user/create";
	
	public static String CRIME_SEARCH			= BASE_URL + "search?criteria=sa&type=cr";
	public static String COMPLAINT_SEARCH		= BASE_URL + "search?criteria=sa&type=co";

	@Before
	public void setupSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\SatnamWaheguru\\Downloads\\chromedriver");
		driver = new ChromeDriver();
	}

	@After
	public void closeSelenium() {
		driver.close();
		driver.quit();
	}
	
	public void doLogin() throws InterruptedException {
		driver.get(LOGIN_URL);
		
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("admin@gmail.com");
		Thread.sleep(1000);
		
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("123456");
		Thread.sleep(1000);
		
		WebElement btnSubmit = driver.findElement(By.id("btnSubmit"));
		btnSubmit.click();
		Thread.sleep(1000);		
	}
	
}
