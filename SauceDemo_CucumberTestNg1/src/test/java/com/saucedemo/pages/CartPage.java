package com.saucedemo.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.saucedemo.framework.WebDriverUtils;

public class CartPage{

	public WebDriver driver;
	
	public static final By checkoutBtn = By.xpath("//button[text()='Checkout']");
	public static final By firstName_textField = By.id("first-name");
	public static final By lastName_textField = By.id("last-name");
	public static final By zipCode_textField = By.id("postal-code");
	public static final By continueBtn = By.xpath("//input[@value='Continue']");
	public static final By finishBtn = By.xpath("//button[text()='Finish']");
	public static final By orderConfirmationMsg = By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']");
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void clickOnCheckOut() 
	{
		WebDriverUtils.ClickButton(checkoutBtn);
	}
	public void clickOnContinue() 
	{
		WebDriverUtils.ClickButton(continueBtn);
	}
	
	public void enterUserDetails(String fName,String lName,String zip) throws Throwable {		
		WebDriverUtils.waitUntilElementEnabled(firstName_textField, 10);
		WebDriverUtils.enterInTextBox(firstName_textField,fName);
		WebDriverUtils.enterInTextBox(lastName_textField,lName);
		WebDriverUtils.enterInTextBox(zipCode_textField,zip);
		}
	
	public void clickOnFinish() throws Throwable 
	{
		WebDriverUtils.ClickButton(finishBtn);
		Boolean isPresent =driver.findElement(orderConfirmationMsg).isDisplayed();
		Assert.assertTrue(isPresent, "THANK YOU FOR YOUR ORDER msg is displayed");
		Thread.sleep(2000);
		
	}

}
