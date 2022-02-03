Feature: Login into Application

Background: Access Launch Page
    Given Initialize the browser with chrome
	
	@scenario
Scenario Outline: Log in to the Application
When I am in the login page <Url> of the application
When User login in application with userName "<userName>" and password "<password>"
And Click on Login btn
Then Verify that user is succesfully logged in "<pageHeading>"
And close browser

Examples:
|userName			|password	  |pageHeading|Url|
|standard_user      |secret_sauce |Products|Url  |

