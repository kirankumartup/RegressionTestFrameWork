package com.company.pages;

import org.openqa.selenium.WebDriver;

import com.company.pages.callScripts.HomePage;


public class Login {
	private WebDriver driver;
	public Login(WebDriver driver){
		this.driver = driver;
	}
	public void perform(String username,String password){
		HomePage.getUserNameTextBox(driver).sendKeys(username);
		HomePage.getPasswordTextBox(driver).sendKeys(password);
		HomePage.getLoginButton(driver).click();
	}
}
