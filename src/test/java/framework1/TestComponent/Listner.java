package framework1.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import framework1.resources.ExtentReporterNg;

public class Listner extends Base_Test implements ITestListener {
	
	ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	ExtentReports extent = ExtentReporterNg.config();

	public void onTestStart(ITestResult result) {

		test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());
		
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String filepath = null;
		try {
			filepath = takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	
	
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test Passed");
	
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
