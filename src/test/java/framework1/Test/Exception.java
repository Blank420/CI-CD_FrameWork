package framework1.Test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework1.TestComponent.Retry;
import framework1.TestComponent.Base_Test;

public class Exception extends Base_Test {


	@Test(retryAnalyzer=Retry.class)

	public void wrongCredentials() throws IOException {


		obj.loginUser("p32dwasd231a1@gmai.com", "P@ss123 word123");
		Assert.assertEquals(obj.errorMessage().getText(), "Incorrect1 email or password.");
	}

	

}
