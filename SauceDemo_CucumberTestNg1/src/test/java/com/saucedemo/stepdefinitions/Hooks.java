package com.saucedemo.stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.saucedemo.framework.BaseClass;

import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.After;

public class Hooks extends BaseClass
{

	
	@After
	public void closeDriver(){
		driver.quit();
	}

	@AfterStep
	public void takeScreenshot(Scenario scenario) throws IOException, InterruptedException{
		if(scenario.isFailed()){
			scenario.attach(BaseClass.getByteScreenshot(), "image/png", "Failed Scenario Screenshot");
			BaseClass.getScreenshot();
		}else{
			scenario.attach(BaseClass.getByteScreenshot(), "image/png", "Screenshot");
		}
		
	}
	
	
//	@After
//	public void atakeScreenshotOnFailure(Scenario scenario)
//	{
//		if(scenario.isFailed())
//		{
//			TakesScreenshot ts=(TakesScreenshot) driver;
//			byte[] src=ts.getScreenshotAs(OutputType.BYTES);
//			scenario.attach(src, "image/png", "Failed SS");
//		}
//	}
	
	
}
