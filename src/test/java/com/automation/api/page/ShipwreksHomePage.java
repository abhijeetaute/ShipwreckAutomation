package com.automation.api.page;

import org.openqa.selenium.WebElement;

public interface ShipwreksHomePage {

	public void launchPage();

	public void waitForPageToLoad();

	public WebElement getTitleLabel();

	public WebElement getImage();
}
