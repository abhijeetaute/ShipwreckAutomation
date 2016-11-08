package com.automation.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.automation.api.page.ShipwreksHomePage;
import com.automation.common.WebDriverTest;

public class ShipwreksTestSuite extends WebDriverTest {

	@Test(enabled = false)
	public void TEST_01() {

		ShipwreksHomePage shipwreksHomePage = getPageFactory().getShipwreksHomePage();
		shipwreksHomePage.launchPage();
	}

	@Test
	public void TEST_02() {

		ShipwreksHomePage shipwreksHomePage = getPageFactory().getShipwreksHomePage();
		shipwreksHomePage.launchPage();

		if (shipwreksHomePage.getTitleLabel().isDisplayed()) {
			Reporter.log("'Title' label successfully displayed");
			String savedText = props.getProperty("shipwreks.home.page.title.text");
			String currentText = shipwreksHomePage.getTitleLabel().getText().trim();
			if (!savedText.equalsIgnoreCase(currentText)) {
				Reporter.log("Not able to verify '" + currentText + "' label");
			} else {
				Reporter.log("'" + currentText + "' label successfully verified");
			}
		} else {
			Assert.assertTrue(shipwreksHomePage.getTitleLabel().isDisplayed(), "Not able to verify 'Title' label");
		}

		if (shipwreksHomePage.getImage().isDisplayed()) {
			Reporter.log("'Image' successfully displayed");
		} else {
			Assert.assertTrue(shipwreksHomePage.getImage().isDisplayed(), "Not able to verify 'Image'");
		}
	}
}
