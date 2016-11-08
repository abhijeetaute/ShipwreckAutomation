package com.automation.page.mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

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
		if (getHumbergerMenu().isDisplayed()) {
			Reporter.log("'Humberger' menu successfully displayed");
		} else {
			Assert.assertTrue(getHumbergerMenu().isDisplayed(), "Not able to verify 'Humberger' menu");
		}
		if (getDriveLocations().isDisplayed()) {
			Reporter.log("'Drive locations' label successfully displayed");
		} else {
			Assert.assertTrue(getDriveLocations().isDisplayed(), "Not able to verify 'Drive locations' label");
		}
		if (getShipWrekLabel().isDisplayed()) {
			Reporter.log("'Ship wrek' label successfully displayed");
		} else {
			Assert.assertTrue(getShipWrekLabel().isDisplayed(), "Not able to verify 'Ship wrek' label");
		}
	}
}
