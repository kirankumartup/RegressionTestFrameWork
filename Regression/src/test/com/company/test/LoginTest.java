package com.company.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.company.pages.Login;
import com.company.pages.callScripts.Dashboard;
import com.company.pages.callScripts.HomePage;
import com.helper.pages.BasePage;

public class LoginTest extends BasePage {
	@Test
	public void sucessfullLoginTest(){
		Login login = new Login(driver);
		login.perform("qamate", "qamate");
		Assert.assertTrue(Dashboard.getWelcomeMessage(driver).getText().contains("Welcome"));
	}
	@Test
	public void rightUserNameWrongPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("qamate", "qamaet");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Invalid credentials"));
	}
	@Test
	public void rightUserNameBlankPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("qamate", "");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Password cannot be empty"));
	}
	@Test
	public void wrongUserNameRightPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("qamaet", "qamate");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Invalid credentials"));
	}
	@Test
	public void wrongUserNameWrongPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("qamaet", "qamet");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Invalid credentials"));
	}
	@Test
	public void wrongUserNameBlankPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("qamaet", "");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Password cannot be empty"));
	}
	
	@Test
	public void blankUserNameRightPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("", "qamate");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Username cannot be empty"));
	}
	@Test
	public void blankUserNameWrongPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("", "qamaet");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Username cannot be empty"));
	}
	@Test
	public void blankUserNameBlankPasswordLoginTest(){
		Login login = new Login(driver);
		login.perform("", "");
		Assert.assertTrue(HomePage.getErrorMessage(driver).getText().contains("Username cannot be empty"));
	}
}
