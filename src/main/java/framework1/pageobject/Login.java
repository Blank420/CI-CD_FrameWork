package framework1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework1.Common_Component.Component;

public class Login extends Component {

	WebDriver driver;

	public Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement submit;
	
 	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement message;

	public Select_item loginUser(String email, String pass) {
		driver.get("https://rahulshettyacademy.com/client");
		userEmail.sendKeys(email);
		userPassword.sendKeys(pass);
		submit.click();
		Select_item pro = new Select_item(driver);
		return pro;

	}
	
	public void goTopage()
	{
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	public WebElement errorMessage()
	{
		visibilityOfWebElement(message);
		return message;
	}

}
