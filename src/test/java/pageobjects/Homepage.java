package pageobjects;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
import testpack.baseClass;
 
	public class Homepage {
		public WebDriver driver;
		public Homepage (WebDriver driver)
		{
		PageFactory.initElements(driver, this);
		}

		//Locators
		@FindBy(linkText="New Bikes")
		WebElement newbikes;
		@FindBy(xpath="//span[text()='Upcoming Bikes']")
		WebElement upcomingbikes;
		@FindBy(xpath="//a[@class='c-p' and text()='Used Cars']")
		WebElement Usedcars;
		@FindBy(xpath="//div[@class='h-dd-r']// span[text()='Chennai']")
		WebElement chennai;
		@FindBy(xpath="//div[@class='col-lg-2']")
		WebElement zigwheels;
		@FindBy(xpath="//div[@class='hidden-xs hidden-sm pull-right']")
		WebElement login;

		//Methods
 
		public void hover(WebElement element)
		{		Actions action = new Actions(baseClass.driver);
 
			action.moveToElement(element).build().perform();
		}
		public void hovernewBikes()
		{
			hover(newbikes);
		}
		public void upcomingbikes()
		{
			upcomingbikes.click();
		}
		public void hoverusedCars()
		{
			hover(Usedcars);
		}

 
		public void chennai()
		{	chennai.click();
		}
		public void zigwheels()
		{
			zigwheels.click();
		}
		public void login()
		{
			login.click();
		}
}