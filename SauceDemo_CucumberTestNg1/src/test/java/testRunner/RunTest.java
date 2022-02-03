package testRunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features= {"src/test/resources/Features/ProductsAndCart.feature"},
		glue="com.saucedemo.stepdefinitions",
		dryRun=false,
		monochrome=true,//removes unwanted symbols from condole window//	
		plugin= {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		tags= ("@AllScenarios")
//		tags= ("@scenario4")
		)

public class RunTest extends AbstractTestNGCucumberTests{

	

}
