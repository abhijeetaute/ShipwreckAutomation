package com.automation.page.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.automation.api.page.ShipwreksHomePage;
import com.automation.common.WebDriverTest;
import com.automation.repository.WebLocators.ShipwreksHomePageLocators;

public class WebShipwreksHomePage extends WebDriverTest implements ShipwreksHomePageLocators, ShipwreksHomePage {

	@FindBy(css = TITLE_LABEL_LOC)
	WebElement titleLabel;
	@FindBy(css = IMAGE_LOC)
	WebElement image;

	public void launchPage() {
		driver.get("http://10.20.32.251:9999/index.html");
		waitForPageToLoad();
	}

	public void waitForPageToLoad() {
		PageFactory.initElements(driver, this);
		waitForPresent(getTitleLabel());
		waitForVisible(getTitleLabel());
	}

	public WebElement getTitleLabel() {
		return titleLabel;
	}

	public WebElement getImage() {
		return image;
	}

	@Override
	public void verifyHomePageDetails() {
		if (getTitleLabel().isDisplayed()) {
			Reporter.log("'Ship wrek' label successfully displayed");
		} else {
			Assert.assertTrue(getTitleLabel().isDisplayed(), "Not able to verify 'Ship wrek' label");
		}
	}
}
