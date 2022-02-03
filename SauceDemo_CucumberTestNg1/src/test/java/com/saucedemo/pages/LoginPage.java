package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.saucedemo.framework.WebDriverUtils;


public class LoginPage{
	public WebDriver driver;
	
	public static final By pageHeading = By.xpath("//span[text()='Products']");
	public static final By userName_textField = By.id("user-name");
	public static final By password_textField = By.id("password");
	public static final By loginBtn = By.xpath("//input[@name='login-button']");
	public static final By loginErrorMsgXpath = By.xpath("//h3[@data-test='error']");
	public static String expectedLoginErrorMsg = "Epic sadface: Username and password do not match any user in this service";
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public void enterUserNameAndPassword(String uname, String pswd) throws Throwable
	{
		WebDriverUtils.enterInTextBox(userName_textField, uname);
		WebDriverUtils.enterInTextBox(password_textField, pswd);
	}

	public void clickOnLoginBtn() 
	{
		WebDriverUtils.ClickButton(loginBtn);
	}
	
	public void verifyPageDisplayed(String expectedTitle) throws Throwable 
	{
		Thread.sleep(1000);
		WebDriverUtils.verifyElementPresent(pageHeading, expectedTitle);
	}
	
	public void checkErrorMsg() 
	{
		String actualLoginErrorMsg = driver.findElement(loginErrorMsgXpath).getText();
		System.out.println("Actual login error msg when entered wrong credentials--> "+actualLoginErrorMsg);
		Assert.assertEquals(actualLoginErrorMsg, expectedLoginErrorMsg);
	}
	
}
