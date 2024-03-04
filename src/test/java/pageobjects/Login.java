package pageobjects;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
public class Login {
 
	public Login (WebDriver driver)
	{
	PageFactory.initElements(driver, this);
	}
	//locators
	@FindBy(xpath="//div[@class='hidden-xs hidden-sm pull-right']")
	WebElement login;
	@FindBy(xpath="//div[contains(@class,'googleSignIn')]")
	WebElement google;
	@FindBy(xpath="//input[@type='email']")
	WebElement email;
	@FindBy(xpath="//span[text()='Next']")
	WebElement next;
	@FindBy(xpath="//div[@class='o6cuMc Jj6Lae']")
	WebElement errormsg;
	//methods
	public void login()
	{
		login.click();
	}
	public void google()
	{
		google.click();
	}
	public void email(String Email)
	{
		email.sendKeys(Email);
	}
	public void next()
	{
		next.click();
	}
	public String errormsg()
	{
		return errormsg.getText();
	}
}