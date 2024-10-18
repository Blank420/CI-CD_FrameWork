package framework1.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Confirmationpage {

	WebDriver driver;

	public Confirmationpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	WebElement code;

	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement confirmationMsg;

	public WebElement isConfirmed() {
		
		return confirmationMsg;
	}
	
	public String code()
	{
		String[] webCode = code.getText().split(" ");

		return webCode[1];
	}

}
