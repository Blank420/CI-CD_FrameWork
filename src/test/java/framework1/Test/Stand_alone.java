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

public class Stand_alone {

	public static void main(String[] args) throws InterruptedException {
		
		String productName="ADIDAS ORIGINAL";
		
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
		

		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("p321@gmai.com");
		driver.findElement(By.id("userPassword")).sendKeys("P@ssword123");
		driver.findElement(By.id("login")).click();
		
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")));
		List<WebElement> products=driver.findElements(By.xpath("//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']"));
				
		
		
		for(WebElement mo : products)
			
		{
			System.out.println(mo.findElement(By.xpath(".//h5/b")).getText());
		}
		
		
		
		
		
		
		
		
		WebElement product =products.stream().filter(s-> s.findElement(By.xpath(".//h5/b")).getText().equals(productName)).findFirst().orElse(null);
		product.findElement(By.xpath(".//button[@class='btn w-10 rounded']")).click();
		
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		//animation for product added
		
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cart=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		boolean t = cart.stream().anyMatch(s-> s.getText().contains(productName));
		
		Assert.assertTrue(t);
		driver.findElement(By.xpath("//li[@class='totalRow']/button[@class='btn btn-primary']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		List<WebElement> location = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		
		WebElement l=location.stream().filter(s-> s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		
		
		l.click();
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		
		String[] code=driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText().split(" ");
		System.out.println(code[1]);
		
		String confirmationMsg=driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("Thankyou for the order."));
	}

}
