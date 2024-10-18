package framework1.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework1.Common_Component.Component;

import org.openqa.selenium.WebElement;

import java.util.List;

public class Select_item extends Component {

	WebDriver driver;

	public Select_item(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")
	List<WebElement> products;

	By pro = By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']");
	By toastContainer = By.cssSelector("#toast-container");

	public List<WebElement> productList() {
		visibilityOfElement(pro);
		return products;
	}

	public WebElement findProduct(String productName) {
		WebElement product = products.stream()
				.filter(s -> s.findElement(By.xpath(".//h5/b")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}

	public void addToCart(String productName) {
		findProduct(productName).findElement(By.xpath(".//button[@class='btn w-10 rounded']")).click();
		visibilityOfElement(toastContainer);
		invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")));
	}



}
