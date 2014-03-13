package com.crimealert;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.crimealert.service.TestComplaintService;
import com.crimealert.service.TestShopService;
import com.crimealert.service.TestUserService;

@RunWith(Suite.class)
@SuiteClasses({TestComplaintService.class,
			   TestComplaintService.class,
			   TestUserService.class,
			   TestShopService.class})
public class AllServiceTests {

}
