package com.automation.page.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.api.page.ShipwreksHomePage;
import com.automation.common.WebDriverTest;
import com.automation.repository.WebLocators.ShipwreksHomePageLocators;

public class WebShipwreksHomePage extends WebDriverTest implements ShipwreksHomePageLocators, ShipwreksHomePage {

	@FindBy(name = TITLE_LABEL)
	WebElement titleLabel;

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
}
