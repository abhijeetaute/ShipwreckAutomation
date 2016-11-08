package com.automation.page.web;

import com.automation.api.PageFactory;
import com.automation.api.page.ShipwreksHomePage;

public class WebPageFactory implements PageFactory {

	@Override
	public ShipwreksHomePage getShipwreksHomePage() {
		return new WebShipwreksHomePage();
	}
}
