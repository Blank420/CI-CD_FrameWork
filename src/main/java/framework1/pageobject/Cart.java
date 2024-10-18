package framework1.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart {

	WebDriver driver;

	public Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> crt;

	@FindBy(xpath = "//li[@class='totalRow']/button[@class='btn btn-primary']")
	WebElement checkout;

	public List<WebElement> itemInCart() {
		return crt;

	}

	public Checkout goToCheckout() {
		checkout.click();
		Checkout check = new Checkout(driver);
		return check;

	}

}
