package com.saucedemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.saucedemo.framework.WebDriverUtils;


public class ProductsPage {

	public WebDriver driver;
	
	public static final By cartLink = By.xpath("//a[@class='shopping_cart_link']");
	public static final By productsList = By.xpath("//div[@class='inventory_item_name']");
	public static final By productDetailsPrice = By.xpath("//div[@class='inventory_details_price']");
	public static final By backToProductsBtn = By.xpath("//button[text()='Back to products']");
	
	public static String productDesc = null;
	public static String productPrice = null;
	public static By productDescBy = null;
	public static By addToCartBtnBy = null;
	public static By productNameBy = null;
	public static By productPriceBy = null;
	
	public ProductsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickOnAddToCartBtnForProduct(String product) throws InterruptedException
	{
	    addToCartBtnBy= WebDriverUtils.getByIdentifier("//div[normalize-space()='"+product+"']//ancestor::div[@class='inventory_item_label']//following-sibling::div//button[text()='Add to cart']", "xpath");
	    System.out.println(addToCartBtnBy);
	    Thread.sleep(2000);
	    WebDriverUtils.waitUntilElementEnabled(addToCartBtnBy,10);
	    WebDriverUtils.ClickButton(addToCartBtnBy);
	}	
	
	public void getProductDescription(String product) throws InterruptedException 
	{
		productDescBy =WebDriverUtils.getByIdentifier("//div[normalize-space()='"+product+"']/../..//div[@class='inventory_item_desc']", "xpath");
		productDesc=driver.findElement(productDescBy).getText();
	}
	
	public void getProductPrice(String product) throws InterruptedException 
	{
		productPriceBy =WebDriverUtils.getByIdentifier("//div[normalize-space()='"+product+"']/../../..//div[@class='inventory_item_price']", "xpath");
		productPrice=driver.findElement(productPriceBy).getText();
	}
	
	public void clickOnCartIcon() 
	{
		WebDriverUtils.ClickButton(cartLink);
	}
	
	public void verifyProductAvailable(String expectedProduct) throws InterruptedException 
	{
		Thread.sleep(2000);
		List<WebElement> ProductsList = driver.findElements(productsList);
		List<String> actualProduct = new ArrayList<String>();
		for (WebElement product : ProductsList) {
			actualProduct.add(product.getText());
		}
		System.out.println("List of Products :-"+actualProduct);//6
		Boolean productPresent = actualProduct.contains(expectedProduct);
		System.out.println("Expected Product: "+expectedProduct+" is Available in products page");
		Assert.assertTrue(productPresent, "Expected Product: "+expectedProduct+" is Available in products page");
		
	}
	
	public void verifyProductDisplayedWithListedPrice(String product) throws Throwable 
	{
		Thread.sleep(2000);
		 int productsName = product.length();
		 if(!(productsName>0)){
		 int i=1;
		
    	 while(WebDriverUtils.checkElementPresentWithString("(//div[@class='inventory_item_name']/../../..//div[@class='inventory_item_price'])["+i+"]","Inventory item price")){
    		 
    		 productPriceBy=WebDriverUtils.getByIdentifier("(//div[@class='inventory_item_name']/../../..//div[@class='inventory_item_price'])["+i+"]", "xpath");
//    		 System.out.println(productPriceBy);
    		 WebDriverUtils.setFocusOnElement(productPriceBy, "product Price"); 
    		 String expectedProductPrice = driver.findElement(productPriceBy).getText().replace("$", "");
    		 
    		 productNameBy=WebDriverUtils.getByIdentifier("(//div[@class='inventory_item_name'])["+i+"]", "xpath");
    		 Boolean productPresent=driver.findElement(productNameBy).isDisplayed();
    		 if(productPresent){
    			 WebDriverUtils.ClickButton(productNameBy);
    			 Thread.sleep(2000);
    			 String actualProductPrice=driver.findElement(productDetailsPrice).getText().replace("$", "");
    			 Assert.assertEquals(actualProductPrice, expectedProductPrice, "Price in products page --> "+expectedProductPrice+" Price in Individual products page --> "+actualProductPrice);
    		     System.out.println("Price in products page --> "+expectedProductPrice+" and Price in Individual products page --> "+actualProductPrice);
    			 WebDriverUtils.ClickButton(backToProductsBtn);
    		     
    		 }
    		     i++;
    		
    	 }
    	 
		 }
		 else{
			 productPriceBy=WebDriverUtils.getByIdentifier("//div[normalize-space()='"+product+"']/../../..//div[@class='inventory_item_price']", "xpath");
			 String expectedProductPrice = driver.findElement(productPriceBy).getText().replace("$", "");
//			 System.out.println("List of Price for product "+product+" without dollar symbol : "+expectedProductPrice);
			 
			 productNameBy=WebDriverUtils.getByIdentifier("//div[normalize-space()='"+product+"']", "xpath");
			 WebDriverUtils.ClickButton(productNameBy);
			 Thread.sleep(2000);
			 String actualProductPrice=driver.findElement(productDetailsPrice).getText().replace("$", "");
			 System.out.println("Price in products page --> "+expectedProductPrice+" and Price in Individual products page --> "+actualProductPrice);
			 Assert.assertEquals(actualProductPrice, expectedProductPrice, "Price in products page --> "+expectedProductPrice+" Price in Individual products page --> "+actualProductPrice);
					
				}
		 
	}
	
	
	public void validateProductDetailsOfProductPresentInCart(String expectedProductName) throws InterruptedException 
	{
		clickOnCartIcon();
//		Thread.sleep(2000);
	    productNameBy =WebDriverUtils.getByIdentifier("//div[@class='cart_item_label']//div[contains(text(),'"+expectedProductName+"')]", "xpath");
	    WebDriverUtils.waitUntilElementVisble(productNameBy, 10);
	    String actualProductName=driver.findElement(productNameBy).getText();
	    System.out.println("Actual Product Name-> "+actualProductName+" ---- Expected Product name-> "+expectedProductName);
	    Assert.assertEquals(actualProductName, expectedProductName);
	    
	    productDescBy =WebDriverUtils.getByIdentifier("//div[normalize-space()='"+expectedProductName+"']/../..//div[@class='inventory_item_desc']", "xpath");
	    String productDesc_CartPage=driver.findElement(productDescBy).getText();
	    System.out.println("Actual Product Description-> "+productDesc_CartPage+" ---- Expected Product Description-> "+productDesc);
	    Assert.assertEquals(productDesc_CartPage, productDesc);
	    
	    productPriceBy =WebDriverUtils.getByIdentifier("//div[normalize-space()='"+expectedProductName+"']/../..//div[@class='inventory_item_price']", "xpath");
	    String productPrice_CartPage=driver.findElement(productPriceBy).getText();
	    System.out.println("Actual Product Price-> "+productPrice_CartPage+" ---- Expected Product Price-> "+productPrice);
	    Assert.assertEquals(productPrice_CartPage, productPrice);
	}

}
