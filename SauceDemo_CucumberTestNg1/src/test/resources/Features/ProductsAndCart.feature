@AllScenarios
Feature: Adding Products to cart and verify product details

Background: Access Launch Page
    Given Initialize the browser with chrome

 @scenario1
Scenario Outline: Log in to the Application
	When I am in the login page <Url> of the application
	When User login in application with userName "<userName>" and password "<password>"
	And Click on Login btn
	Then Verify that user is succesfully logged in "<pageHeading>"
	And Add the product "<product>" to the cart
	And Get description and price of product "<product>" from Products page
	Then Click on Cart icon and validate the product details of product "<product>" from Cart page
	And Click on Checkout btn
	Then Enter user First name "<fName>" last name "<lName>" and Postal code "<zipCode>" 
	And Click on Continue btn
	Then Click on Finish btn
#	And close browser

	Examples:
		|userName			|password	  |Url|pageHeading|product                |fName  |lName|zipCode   |
		|standard_user      |secret_sauce |Url|Products   |Sauce Labs Bolt T-Shirt|Soumya |Rathi|201001    |
    
 @scenario2
Scenario Outline: Log in and verify Product is present in Products page
	When I am in the login page <Url> of the application  
	When User login in application with userName "<userName>" and password "<password>"
	And Click on Login btn
	Then Verify that user is succesfully logged in "<pageHeading>"
	And Verify the product "<product>" is available
#	And close browser
		
	Examples: 
		|userName     |password     |Url|pageHeading|product                |
		|standard_user|secret_sauce |Url|Products   |Sauce Labs Bolt T-Shirt|
	
@scenario3
Scenario Outline: Log in and verify Product Price is displayed correctly in Individual Product page
	When I am in the login page <Url> of the application  
	When User login in application with userName "<userName>" and password "<password>"
	And Click on Login btn
	Then Verify that user is succesfully logged in "<pageHeading>"
    And verify the price of product "<product>" is correctly listed in product individual page
#	And close browser
		
	Examples: 
		|userName     |password     |Url|pageHeading|product            |
#		|standard_user|secret_sauce |Url|Products   |Sauce Labs Bolt T-Shirt|
        |standard_user|secret_sauce |Url|Products   |    |
		
@scenario4
Scenario Outline: Log in with wrong Credentials and validate Error Message 
	When I am in the login page <Url> of the application  
	When User login in application with userName "<userName>" and password "<password>"
	And Click on Login btn
	Then Validate Error msg displayed when credentials are Invalid
#	And close browser 
	
	Examples: 
		|userName     |password     |Url|
		|wrong_user   |wrong_pswrd  |Url|
		