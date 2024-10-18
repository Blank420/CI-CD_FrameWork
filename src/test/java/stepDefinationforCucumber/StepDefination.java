package stepDefinationforCucumber;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import framework1.TestComponent.Base_Test;
import framework1.pageobject.Cart;
import framework1.pageobject.Checkout;
import framework1.pageobject.Confirmationpage;
import framework1.pageobject.Login;
import framework1.pageobject.Select_item;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends Base_Test {
	Login obj;
	Select_item pro;
	Cart c;
	Checkout check;
	Confirmationpage conf;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Eommerce_page() throws IOException {
		obj = openURL();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		pro = obj.loginUser(username, password);
	}

	@When("^I add a product (.+) in cart$")
	public void i_add_a_product_in_cart(String productName) {
		pro.addToCart(productName);
	}

	@And("^checkout (.+) and submit order$")
	public void checkout_and_submit_order(String productName) {
		c = pro.goToCart();

		List<WebElement> crt = c.itemInCart();

		boolean t = crt.stream().anyMatch(s -> s.getText().contains(productName));

		Assert.assertTrue(t);

		check = c.goToCheckout();
	}

	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String message) {

		check.selectCountry("Ind", "India");
		conf = check.placeOrder();

		WebElement confirmationMsg = conf.isConfirmed();
		Assert.assertTrue(confirmationMsg.getText().equalsIgnoreCase(message));

		String code = conf.code();
		System.out.println(code);

	}
	
	@Then("{string} message is displayed on login page")
	public void message_is_displayed_on_login_page(String message)
	{
		
		Assert.assertEquals(obj.errorMessage().getText(), message);
		
	}

}
