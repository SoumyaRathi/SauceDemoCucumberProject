package com.saucedemo.stepdefinitions;

import com.saucedemo.framework.BaseClass;
import com.saucedemo.framework.WebDriverUtils;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;

//import cucumber.api.java.en.*;

import io.cucumber.java.en.*;

public class StepDefintions extends BaseClass{
	


	@Given("Initialize the browser with chrome")
	public void initialize_the_browser_with_chrome() throws Throwable {
		
		setup();
		logger.info("******** Launching browser*********");
		login=new LoginPage(driver);
		products = new ProductsPage(driver);
		cart = new CartPage(driver);
		WbUtil= new WebDriverUtils(driver);
	}
	
	@When("I am in the login page Url of the application")
	 public void i_am_in_the_login_page_of_the_application() throws Throwable {
		 logger.info("******** Opening URL*********");
		 driver.get(prop.getProperty("Url"));
		 System.out.println(prop.getProperty("Url"));
		 driver.manage().window().maximize();
	    }
	
	@When("User login in application with userName {string} and password {string}")
	public void user_login_in_application_with_userName_and_password(String username, String password) throws Throwable {
		logger.info("******** Give login details*********");
		System.out.println(username+" "+password);
		login.enterUserNameAndPassword(username, password);
	}

	@When("Click on Login btn")
	public void click_on_Login_btn() throws Throwable {
		logger.info("********click Login button *********");
    	login.clickOnLoginBtn();
	}

	@Then("Verify that user is succesfully logged in {string}")
	public void verify_that_user_is_succesfully_logged_in(String title) throws Throwable {
		logger.info("********Verify whether user logged in or not *********");
    	login.verifyPageDisplayed(title);
    	logger.info("********User logged in *********");
	}
	
	@Then("Add the product {string} to the cart")
	public void add_the_product_to_the_cart(String product) throws Throwable {
		logger.info("********Adding product to Cart*********");
    	products.clickOnAddToCartBtnForProduct(product);
	}

	@Then("Get description and price of product {string} from Products page")
	public void get_description_and_price_of_product_from_Products_page(String product) throws Throwable {
		logger.info("********Getting Description and Price of Product from products page*********");
    	products.getProductDescription(product);
    	products.getProductPrice(product);
	}

	@Then("Click on Cart icon and validate the product details of product {string} from Cart page")
	public void click_on_Cart_icon_and_validate_the_product_details_of_product_from_Cart_page(String product) throws Throwable {
		logger.info("********Validate Product details displayed correctly in Cart page*********");
    	products.validateProductDetailsOfProductPresentInCart(product);
	}

	@Then("Click on Checkout btn")
	public void click_on_Checkout_btn() throws Throwable {
		logger.info("********Clicking on Checkout btn********");
    	cart.clickOnCheckOut();
	}

	@Then("Enter user First name {string} last name {string} and Postal code {string}")
	public void enter_user_First_name_last_name_and_Postal_code(String fName, String lName, String zipcode) throws Throwable {
		logger.info("********Enter Required User Details********");
    	cart.enterUserDetails(fName, lName, zipcode);
	}

	@Then("Click on Continue btn")
	public void click_on_Continue_btn() throws Throwable {
		logger.info("********Clicking on Continue btn********");
    	cart.clickOnContinue();
	}

	@Then("Click on Finish btn")
	public void click_on_Finish_btn() throws Throwable {
		logger.info("********Clicking on Finish btn********");
    	cart.clickOnFinish();
	}


	@Then("Verify the product {string} is available")
	public void verify_the_product_is_available(String product) throws Throwable {
		logger.info("********Verify Product Present in Products page********");
    	products.verifyProductAvailable(product);
	}
	
	@Then("verify the price of product {string} is correctly listed in product individual page")
	public void verify_the_price_of_product_is_correctly_listed_in_product_individual_page(String product) throws Throwable {
		logger.info("********Verify Product Price is Correctly Listed in Product Individual page********");
    	products.verifyProductDisplayedWithListedPrice(product);
	}
	
	@Then("Validate Error msg displayed when credentials are Invalid")
    public void validate_error_msg_displayed_when_credentials_are_invalid() throws Throwable {
    	logger.info("********Validate Error Msg if Credentials Invalid********");
    	login.checkErrorMsg();
    }

	@Then("close browser")
	public void close_browser() throws Throwable {
		logger.info("********closing browser********");
		driver.quit();
	}

}
