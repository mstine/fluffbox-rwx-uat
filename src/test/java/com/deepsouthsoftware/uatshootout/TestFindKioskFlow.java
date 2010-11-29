package com.deepsouthsoftware.uatshootout;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFindKioskFlow extends SeleneseTestBase {			
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8081/");
		selenium.start();
	}

	@Test
	public void testFindKioskFlow() throws Exception {
		selenium.open("/fluffbox-rwx-0.1/");
		selenium.click("//a[contains(@href, '/fluffbox-rwx-0.1/kiosk/find')]");
		selenium.waitForPageToLoad("30000");
		selenium.type("searchCriteria", "Ft. Lauderdale");
		selenium.click("searchButton");
		
		for (int second = 0;; second++) {
			if (second >= 60) 
				fail("timeout");
			try { 
				if (selenium.isElementPresent("link=Find Speakers Here"))
				 break; 
				} catch (Exception e) {}
			Thread.sleep(1000);
		}
		selenium.click("link=Find Speakers Here");
		
		
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Aleksandar Seovic");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=RENT NOW!");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Continue");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("This concludes your fake rental experience!"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
