package testpack;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {    //or extends TestListenerAdapter 
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods
public void onStart(ITestContext context) {
	sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/report/myReport.html");//specify location of the report
	sparkReporter.config().setDocumentTitle("Automation Report"); // TiTle of report
	sparkReporter.config().setReportName("Identify NewBikes"); // name of the report
	sparkReporter.config().setTheme(Theme.DARK);		
	extent=new ExtentReports();
	extent.attachReporter(sparkReporter);	
	extent.setSystemInfo("Computer Name","localhost");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Tester Name","Anil Kumar Mavuri");
	extent.setSystemInfo("os","Windows11");
	extent.setSystemInfo("Browser name","Chrome,Edge");
}
public void onTestSuccess(ITestResult result) {
	test = extent.createTest(result.getName()); // create a new entry in the report
	test.log(Status.PASS, "TEST CASE PASSED IS  :" + result.getName()); // update status p/f/s

	File fileobj=((TakesScreenshot)baseClass.driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(fileobj, new File(System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	test.addScreenCaptureFromPath(System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png"); 
}

public void onTestFailure(ITestResult result) {
	test = extent.createTest(result.getName());
	test.log(Status.FAIL, "Test case FAILED is:" + result.getName());
	test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable()); 
}

public void onTestSkipped(ITestResult result) {

	test = extent.createTest(result.getName());
	test.log(Status.SKIP, "Test case SKIPPED is:" + result.getName());
}


public void onFinish(ITestContext context) {
	extent.flush();
}

}
