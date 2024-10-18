package framework1.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout {

	WebDriver driver;

	public Checkout(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement contry;
	@FindBy(xpath = "//button[@class='ta-item list-group-item ng-star-inserted']")
	List<WebElement> location;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrderBtn;

	public List<WebElement> userCountry(String country) {
		contry.sendKeys(country);
		return location;

	}

	public void selectCountry(String enterValue, String country) {

		WebElement l = userCountry(enterValue).stream().filter(s -> s.getText().equalsIgnoreCase(country)).findFirst()
				.orElse(null);
		l.click();
	}

	public Confirmationpage placeOrder()

	{
		placeOrderBtn.click();
		Confirmationpage conf = new Confirmationpage(driver);
		return conf;
	}

}
