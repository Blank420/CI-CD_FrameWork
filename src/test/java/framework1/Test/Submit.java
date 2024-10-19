package framework1.Test;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import framework1.TestComponent.Base_Test;
import framework1.pageobject.Cart;
import framework1.pageobject.Checkout;
import framework1.pageobject.Confirmationpage;
import framework1.pageobject.Select_item;

public class Submit extends Base_Test {

	@Test(dataProvider = "getData", groups = { "Datadriven" })
	public void submitUser(HashMap<String, String> item) throws InterruptedException, IOException {

		Select_item pro = obj.loginUser(item.get("email"), item.get("password"));

		System.out.println(item.get("email"));
		System.out.println(item.get("password"));
		System.out.println(item.get("productName"));

		pro.addToCart(item.get("productName"));
		Cart c = pro.goToCart();

		List<WebElement> crt = c.itemInCart();

		boolean t = crt.stream().anyMatch(s -> s.getText().contains(item.get("productName")));

		Assert.assertTrue(t);

		Checkout check = c.goToCheckout();

		check.selectCountry("Ind", "India");
		Confirmationpage conf = check.placeOrder();

		WebElement confirmationMsg = conf.isConfirmed();
		Assert.assertTrue(confirmationMsg.getText().equalsIgnoreCase("Thankyou for the order."));

		String code = conf.code();
		System.out.println(code);
		System.out.println("WebHooks for CI/CD");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
	
		
		
		List<HashMap<String,String>> data =getJsonDataToMap("D:\\Framework\\Framework\\src\\test\\java\\framework1\\TestData\\TestData.json");

		//return new Object[][] {{data.get(0)},{data.get(1)}};
		
		Object[][] result = new Object[data.size()][1]; // One column for each map

		// Fill the result array using a loop
		for (int i = 0; i < data.size(); i++) {
		    result[i][0] = data.get(i); // Each row contains one HashMap
		}

		return result;
		
		
		/*HashMap<Object, Object> data = new HashMap<Object, Object>();
		data.put("email", "test090@gmail.com");
		data.put("password", "P@ssword1234");
		data.put("productName", "ADIDAS ORIGINAL");

		HashMap<Object, Object> data1 = new HashMap<Object, Object>();
		data1.put("email", "p321@gmail.com");
		data1.put("password", "P@ssword123");
		data1.put("productName", "ZARA COAT 3");

		return new Object[][] { { data }, { data1 } };
		*/

		// return new Object[][] {{"test090@gmail.com","P@ssword1234","ADIDAS ORIGINAL"},{"p321@gmail.com","P@ssword123","ZARA COAT 3"}};

	}

}
