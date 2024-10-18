package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="stepDefinationforCucumber",monochrome=true,tags="@Exception",plugin= {"html:reports/cucmberreport.htm"})
public class Cucumber_runner extends AbstractTestNGCucumberTests {

	
}
