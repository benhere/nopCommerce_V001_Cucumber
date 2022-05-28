package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		 features = ".//Feature_Description/Customers.feature",
		 
		 //run all Scenarios of Feature file at once
		 //features = ".//Feature_Description/",
		 
		 glue = "stepDefinitions",
		 //dryrun command will dry run the StepDetails file and check whether all steps working fine or not
		 dryRun = false,
		 //here monochrome command  will remove unnecessary characters from Console screen -
		 monochrome = true,
		 //here we are using plugin to print pretty output with html report
		 plugin = {"pretty","html:test-output"}
		 //tags = ("@sanity, @regression")
)
public class TestRunner {

}
