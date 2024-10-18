package framework1.Common_Component;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework1.pageobject.Cart;

public class Component {

	WebDriver driver;

	public Component(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;

	public void visibilityOfElement(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void invisibilityOf(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		w.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public void visibilityOfWebElement(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		w.until(ExpectedConditions.visibilityOf(ele));

	}	
	public Cart goToCart()
	{
		visibilityOfWebElement(cartBtn);
		cartBtn.click();
		Cart c = new Cart(driver);
		return c;
	}
}
