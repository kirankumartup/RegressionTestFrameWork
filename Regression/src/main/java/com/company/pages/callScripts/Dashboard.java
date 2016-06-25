package com.company.pages.callScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.helper.webdriver.BasePageObjects;

public class Dashboard extends BasePageObjects {
	private static final By byWelcome = By.id("welcome");
	
	public static WebElement getWelcomeMessage(WebDriver driver){
		return driver.findElement(byWelcome);
	}
}
