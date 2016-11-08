package com.automation.test;

import org.testng.annotations.Test;

import com.automation.api.page.ShipwreksHomePage;
import com.automation.common.WebDriverTest;

public class ShipwreksTestSuite extends WebDriverTest {

	@Test
	public void TC_DEMO_01() {

		ShipwreksHomePage shipwreksHomePage = getPageFactory().getShipwreksHomePage();
		shipwreksHomePage.launchPage();
	}
}
