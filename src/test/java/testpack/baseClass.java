package testpack;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageobjects.Homepage;
import pageobjects.Login;
import pageobjects.Upcomingbikes;
import pageobjects.Usedcars;

public class baseClass {
	public static List<String>bikemodels=new ArrayList<String>();
	public static List<String>bikeprices=new ArrayList<String>();
	public static List<String>launchdates=new ArrayList<String>();
	public static List<String>popularmodels=new ArrayList<String>();

	public static WebDriver driver;
	Homepage hp;
	Login li;
	Upcomingbikes ub;
	Usedcars uc;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void setup(String browser,String url) throws IOException {
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new FirefoxDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ScreenShot("Homepage");
	}
	@Test(priority=1)
	public void newbikes() throws IOException, InterruptedException {
		hp=new Homepage(driver);
		hp.hovernewBikes();
		ScreenShot("HoverNewBikes");
		hp.upcomingbikes();	
		Thread.sleep(3000);
		ScreenShot("UpComingbikes");

	}
	@Test(priority=2)
	public void bikeslist() throws IOException {
		Upcomingbikes ub=new Upcomingbikes(driver);
		ub.manufactures();
		ScreenShot("manufacture");
		ub.honda();
		ScreenShot("Honda");
		ub.readmore();
		Scroll();
		ScreenShot("bikelist");
		priceIndex(ub.bikeprices());
		 System.out.println("_____________________________________________________________________________________________");
		Convert(ub.bikemodels(),ub.bikeprices(),ub.launchdates());
		
	}
	public static List<Integer> index= new ArrayList<Integer>();
	public void priceIndex(List<String>price) {
	for(int i=0; i<price.size();i++)
	{
		String value= price.get(i).replaceAll("[^0-9]","");
		if(Integer.parseInt(value)*1000<400000 && !(price.get(i).contains("crore")))
		{	
	        index.add(i);
		}
    	else if(!(price.get(i).contains("Lakh"))) {
			index.add(i);
    	}
	}
	}
	public void Convert(List<String>model,List<String>price,List<String>dates) {
		for(int i=0;i<model.size();i++)
    	{
    		if (index.contains(i))
    		{
    			System.out.println(model.get(i) + "----" + price.get(i) + "----"  + dates.get(i) );
    			bikemodels.add(model.get(i));
    			bikeprices.add(price.get(i));
    			launchdates.add(dates.get(i));
    		}
    	}
	}
	public void Scroll() {
		JavascriptExecutor js= (JavascriptExecutor)driver;	
		js.executeScript("window.scrollBy(0,400)", "");
		}
	@Test(priority=3)
	public void usedcars() throws InterruptedException, IOException {
		hp.zigwheels();
		Usedcars uc=new Usedcars(driver);
		hp.hoverusedCars();
		Thread.sleep(5000);
		ScreenShot("hoverUsedcars");
		hp.chennai();
		Thread.sleep(3000);
		ScreenShot("chennai");
		System.out.println("_____________________________________________________________________________________________");
		
		for(String Popular:uc.popularmodels()) {
			System.out.println(Popular);
			popularmodels.add(Popular);
		}

	}
	@DataProvider(name="Input")
	public Object[][] dataProvider() throws IOException {
		FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		Properties p=new Properties();
		p.load(file);		
		return new Object[][] {{p.getProperty("email")}};
	}	
	
	
	@Test(priority=4,dataProvider="Input")
	public void Account(String mail) throws InterruptedException, IOException {
		Login li=new Login(driver);
		hp.zigwheels();
		li.login();
		Thread.sleep(3000);
		ScreenShot("login");
		li.google();
		Thread.sleep(3000);
		ScreenShot("google");
		Set <String> win=driver.getWindowHandles();
		 List<String>windows=new ArrayList<String>(win);
		 driver.switchTo().window(windows.get(1));
		 li.email(mail);
		 ScreenShot("Email");
		 li.next();
		 Thread.sleep(10000);
		 ScreenShot("Error");
		 li.errormsg();
		 System.out.println("_____________________________________________________________________________________________");
         final String ANSI_RESET = "\u001B[0m"; 
		 final String ANSI_RED = "\033[1;31m";
		 System.out.println("Error msg is :"+ANSI_RED + li.errormsg() + ANSI_RESET);
		 Utility.excelOutput(bikemodels, bikeprices, launchdates, popularmodels);
	}
	
	public void ScreenShot(String Name) throws IOException {
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		File src=ts.getScreenshotAs(OutputType.FILE);
//		File tar=new File("C:\\Users\\2303971\\eclipse-workspace\\BikesTestNG\\ActionSS\\");
//		FileUtils.copyFile(src, tar+Name+".png");
		File fileobj=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fileobj, new File("C:\\Users\\2303971\\eclipse-workspace\\BikesTestNG\\ActionSS\\"+Name+".png"));
	}
	@AfterClass
	public void Flush() {
		driver.quit();
	}
}
