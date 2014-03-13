package com.crimealert;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.crimealert.ui.TestUIComment;
import com.crimealert.ui.TestUIComplaint;
import com.crimealert.ui.TestUICrime;
import com.crimealert.ui.TestUILogin;
import com.crimealert.ui.TestUINavigation;
import com.crimealert.ui.TestUIRegister;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestUILogin.class, 
					  TestUIRegister.class,
					  TestUINavigation.class,
					  TestUICrime.class,
					  TestUIComplaint.class,
					  TestUIComment.class} )
public final class AllUITests {

}