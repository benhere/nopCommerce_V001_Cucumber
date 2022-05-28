package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	
	public WebDriver wd;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage sCus;
	public static Logger logger;
	public Properties configProp;
	
	//Created for generating Random string for unique email Id
	public static String randomStringGeneration() {
		//this randomAlphabetic() method will create and generate 5-characters long String
		String generateString1 = RandomStringUtils.randomAlphabetic(5);
		return generateString1;
	}
}
