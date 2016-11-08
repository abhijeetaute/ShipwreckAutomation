package com.automation.page.mobile;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.automation.api.page.ShipwreksHomePage;
import com.automation.common.WebDriverTest;
import com.automation.repository.MobileLocators.ShipwreksHomePageLocators;

public class MobileShipwreksHomePage extends WebDriverTest implements ShipwreksHomePageLocators, ShipwreksHomePage {

	@FindBy(xpath = TITLE_LABEL_LOC)
	WebElement titleLabel;
	@FindBy(xpath = IMAGE_LOC)
	WebElement image;
	@FindBy(xpath = BUTTON_HAMBERGERMENU_LOC)
	WebElement humbergerMenu;

	@FindBy(xpath = LABEL_SHIPWREKDRIVELOCATIONS_LOC)
	WebElement driveLocations;

	@FindBy(xpath = BUTTON_CLICKHERE_LOC)
	WebElement btnClickHere;

	@FindBy(xpath = LABEL_SHIPWREKLIST_LOC)
	WebElement shipWrekLabel;


	public WebElement getHumbergerMenu() {
		return humbergerMenu;
	}

	public WebElement getDriveLocations() {
		return driveLocations;
	}

	public WebElement getBtnClickHere() {
		return btnClickHere;
	}

	public WebElement getShipWrekLabel() {
		return shipWrekLabel;
	}

	public void setTitleLabel(WebElement titleLabel) {
		this.titleLabel = titleLabel;
	}

	public void setImage(WebElement image) {
		this.image = image;
	}

	public void launchPage() {
		waitForPageToLoad();
	}

	public void waitForPageToLoad() {
		PageFactory.initElements(driver, this);
		waitForPresent(getShipWrekLabel());
		waitForVisible(getShipWrekLabel());
	}

	public WebElement getTitleLabel() {
		return titleLabel;
	}
	public WebElement getImage() {
		return image;
	}
	
	public void verifyHomePageDetails(){
		waitForPresent(getHumbergerMenu());
		Assert.assertTrue(getHumbergerMenu().isDisplayed(), "humberger menu");
		//Assert.assertTrue(getBtnClickHere().isDisplayed(), "click here button");
		Assert.assertTrue(getDriveLocations().isDisplayed(), "Drive locations label");
		Assert.assertTrue(getShipWrekLabel().isDisplayed(), "Ship wrek label");
	}
}
