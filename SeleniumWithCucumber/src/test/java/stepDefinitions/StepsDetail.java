package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

@SuppressWarnings("unused")
public class StepsDetail extends BaseClass {
	
	@Before
	public void setup() throws IOException
	{
	    //add logger
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");  //logger added

		//reading Properties file
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configProp.load(configPropFile);
		
		String brName = configProp.getProperty("browser");
		
		if(brName.equals("chrome")) {
			//choose required driver.......
		    System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		    wd = new ChromeDriver();
		}
		else if(brName.equals("firefox")) {
			//choose required driver.......
		    System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
		    wd = new FirefoxDriver();
		}
		else if(brName.equals("ie")) {
			//choose required driver.......
		    System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
		    wd = new InternetExplorerDriver();
		}
		
		//write into log file using logger Object
		logger.info("****** Launching browser ******");
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {    
	    lp = new LoginPage(wd);
	}

	@SuppressWarnings("deprecation")
	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		//here we are url from FeatureDescription-->Login.feature file
		
	    wd.manage().window().maximize(); //maximize window
	    wd.manage().deleteAllCookies();  //delete all the cookies
	    
	    //dynamic wait
	    wd.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
	    //implicitly wait
	    wd.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

	    logger.info("****** Opening URL in the browser ******");
		wd.get(url);
	}

	@When("User enters Email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("****** Providing Login details ******");
		// here we are passing email and password using feature file
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("click on Login")
	public void click_on_login() throws InterruptedException {
		logger.info("****** started login ******");
		// click on login button 
		lp.clickLogin();
		Thread.sleep(2100);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	    //here we are passing title using Feature file
		
		if(wd.getPageSource().contains("Login was unsuccessful.")) {
			wd.close();
			logger.info("****** Login to Application is successfull!!! ******");
			Assert.assertTrue(false);
		}else {
			logger.info("****** Login Failed ******");
			Assert.assertEquals(title, wd.getTitle());
		}
		Thread.sleep(2100);
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		logger.info("****** Now click on logout button ******");
		// click on Logout link to logout from Application
		lp.clickLogout();
		Thread.sleep(2500);
		//after this method internally page_title_should_be(String title) will be called again
		//as it is mention in Feature file and it will validate logout functionality working fine or not
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("****** Closing browser ******");
	    // after all operation close the Browser
		wd.quit();
	}
	
	//Add Customer feature step definitions.....
	//Implement all below methods using Page Object class
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		addCust = new AddCustomerPage(wd);
		String expTitle = "Dashboard / nopCommerce administration";
		String actualTitle = addCust.getPageTitle();
		Assert.assertEquals(expTitle,actualTitle);
	}
	
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(2500);
	    addCust.clickOnCustomersMenu();
	}
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
	    addCust.clickOnCustomersMenuItem();
	}
	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
	    addCust.clickOnAddNew();
	    Thread.sleep(2100);
	}
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() throws InterruptedException {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("****** Adding new Customer ******");
		logger.info("****** Providing Customer details ******");
		String email = randomStringGeneration()+"@gmail.com";
	    addCust.setEmail(email);
	    addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
	    
	    addCust.setCustomerRoles("Guest");
	    Thread.sleep(2500);
	    
	    addCust.setManagerofVendor("Vendor 2");
	    addCust.setGender("Male");
	    addCust.setFirstName("Sudo");
	    addCust.setLastName("King");
	    addCust.setDob("8/09/1998");
	    addCust.setCompanyName("Walmart");
	    addCust.setAdminContent("This is for demo testing.......");
	}
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("****** Save Customer details ******");
		addCust.clickOnSave();
	    Thread.sleep(3500);
	}
	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	    Assert.assertTrue(wd.findElement(By.tagName("body")).getText()
	    		.contains("The new customer has been added successfully"));
	}
	
	//Steps for Searching a Customer using Email ID.......
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		logger.info("****** Searching a customer by using Email Id ******");
		sCus = new SearchCustomerPage(wd);
		sCus.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    sCus.clickSearch();
	    Thread.sleep(2500);
	}
	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	    boolean status = sCus.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	    Assert.assertEquals(true, status);
	}
	
	//steps for Searching a Customer by using FirstName and LastName
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		logger.info("****** Searching customer by Name ******");
	    sCus = new SearchCustomerPage(wd);
	    sCus.setFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
	    sCus.setLasttName("Terces");
	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
	    boolean nameStatus = sCus.searchCustomerByName("Victoria Terces");
	    Assert.assertEquals(true, nameStatus);
	}
}
