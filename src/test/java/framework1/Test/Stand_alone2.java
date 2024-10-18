package framework1.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework1.pageobject.Cart;
import framework1.pageobject.Checkout;
import framework1.pageobject.Confirmationpage;
import framework1.pageobject.Login;
import framework1.pageobject.Select_item;

public class Stand_alone2 {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		Login obj = new Login(driver);

		Select_item pro = obj.loginUser("p321@gmai.com", "P@ssword123");

		List<WebElement> products = pro.productList();
		pro.addToCart(productName);
		Cart c = pro.goToCart();

		List<WebElement> crt = c.itemInCart();

		boolean t = crt.stream().anyMatch(s -> s.getText().contains(productName));

		Assert.assertTrue(t);

		Checkout check = c.goToCheckout();

		check.selectCountry("Ind", "India");
		Confirmationpage conf = check.placeOrder();

		WebElement confirmationMsg = conf.isConfirmed();
		Assert.assertTrue(confirmationMsg.getText().equalsIgnoreCase("Thankyou for the order."));
		
		String code = conf.code  ();
		System.out.println(code);
	}

}
