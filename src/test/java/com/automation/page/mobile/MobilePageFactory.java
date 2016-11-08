package com.automation.page.mobile;

import com.automation.api.page.ShipwreksHomePage;

public class MobilePageFactory implements com.automation.api.PageFactory {

	@Override
	public ShipwreksHomePage getShipwreksHomePage() {
		return new MobileShipwreksHomePage();
	}
}
