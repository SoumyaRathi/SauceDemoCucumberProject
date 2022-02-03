package com.saucedemo.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverUtils{

	private static WebDriver driver;
	
	public WebDriverUtils(WebDriver driver){
		this.driver = driver;
	}
	
//	Explicit wait
	public static void waitUntilElementVisble(By by, long timeOutInSeconds) {
		WebDriverWait wait=new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

//	Explicit wait
	public static void waitUntilElementEnabled(By by, long timeOutInSeconds) {
		WebDriverWait wait=new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
//	entering text
	public static void enterInTextBox(By elementAddress, String text) throws Exception {
		    waitUntilElementVisble(elementAddress, 20);
			driver.findElement(elementAddress).clear();
			Thread.sleep(100);
			driver.findElement(elementAddress).sendKeys(text);
	}
	
//for clicking button
		public static void ClickButton(By elementAddress) 
		{ 
			waitUntilElementEnabled(elementAddress, 20);
			driver.findElement(elementAddress).click();
		}
		
		public static By getByIdentifier(String xpathText, String identifyBy) {
			By identifier;
			if (identifyBy.equalsIgnoreCase("id")) {
				identifier = By.id(xpathText);
			} else {
				identifier = By.xpath(xpathText);
			}
			return identifier;

		}
		
//		to verify element present
		public static boolean verifyElementPresent(By elementAddress, String elementName) throws Throwable {
			WebElement e = null;
			boolean found = false;
			try {
				waitUntilElementVisble(elementAddress, 10);
				e = driver.findElement(elementAddress);
			    found = e.isDisplayed();
				System.out.println(elementName + " is displayed");
			} catch (Exception exc) {
				System.out.println(elementName + " is NOT displayed");
				throw new Exception();
			}

			return found;
		}

//		to focus on element
		public static void setFocusOnElement(By identifier, String elementName) {
			WebElement element = null;
			try {
				element = driver.findElement(identifier);
				Actions action = new Actions(driver);
				action.moveToElement(element).build().perform();
			} catch (Exception e) {
				System.out.println("Unable to focus on element " + elementName);
			}
		}
		
		public static boolean checkElementPresentWithString(String elementAddress, String elementName) throws Throwable {
			WebElement e = null;
			boolean found = false;
			try {
				e = driver.findElement(By.xpath(elementAddress));
			    found = e.isDisplayed();
				System.out.println(elementName + "element is displayed");
			} catch (Exception exc) {
				System.out.println(elementName + "element is NOT displayed");
			}

			return found;
		}
		
		/*	public static Boolean verifyPageTitle(String expectedTitle) throws Exception {
		String actualTitle = driver.getTitle();
		if (!actualTitle.contentEquals(expectedTitle)) {
			System.out.println("User is not logged into page title "+actualTitle);
			throw new Exception();
		}else{
			System.out.println("User successfully logged into page title "+actualTitle);
		}
		return true;
	}*/

}
