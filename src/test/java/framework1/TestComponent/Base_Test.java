package framework1.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework1.pageobject.Login;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Test {

	public WebDriver driver;
	public Login obj;

	public WebDriver browserSetup() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				/* System.getProperty("user.dir")+ */"D:\\Framework\\Framework\\src\\main\\java\\framework1\\resources\\Globaldata.properties");
		prop.load(file);
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");


		if (browser.contains("chrome")) {
			
			ChromeOptions opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless"))
			{
				opt.addArguments("headless");
			}
			
			driver = new ChromeDriver(opt);
			driver.manage().window().setSize(new Dimension(1440,900));

		}

		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver();
			//System.setProperty("webdriver.geckodriver.driver", "D:\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		return driver;

	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Hashmap - jackson databind

		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public Login openURL() throws IOException {
		driver = browserSetup();
		obj = new Login(driver);
		obj.goTopage();
		return obj;

	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
	}

	public void quitBrowser() {
		driver.quit();
	}

	public String takeScreenshot(String testname ,WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testname + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testname + ".png";

	}

}
