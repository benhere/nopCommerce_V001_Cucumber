package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelpher;

public class SearchCustomerPage {
	
	public WebDriver wd;
	WaitHelpher wh;
	
	public SearchCustomerPage(WebDriver wd1) 
	{
		wd = wd1;
		//Using the initElements method, 
		//one can initialize all the web elements located by @FindBy annotation
		PageFactory.initElements(wd, this);
		wh = new WaitHelpher(wd);
	}
	
	//@CacheLookup - This annotation, when applied over a WebElement, 
	// instructs Selenium to keep a cache of the WebElement instead of searching 
	// for the WebElement every time from the WebPage. 
	// This helps us save a lot of time.
	
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobMonth;
	
	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;
	
	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	List<WebElement> tableColumns;

	public void setEmail(String email)
	{
		wh.waitForWebElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	public void setFirstName(String fname)
	{
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLasttName(String lname)
	{
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clickSearch()
	{
		btnSearch.click();
		wh.waitForWebElement(btnSearch, 25);
	}
	
	public int getNoOfRows() {
		return(tableRows.size());
	}
	
	public int getNoOfColumns() {
		return(tableColumns.size());
	}
	
	//method to Search Customer using Email
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag = false;
		for(int i=1; i<=getNoOfRows(); i++)
		{
			String emailId = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]")).getText();
		    
			System.out.println(emailId);
			
			if(emailId.equals("victoria_victoria@nopCommerce.com")) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String Name)
	{
		boolean flag = false;
		for(int i=1; i<=getNoOfRows(); i++)
		{
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]")).getText();

			System.out.println(name);
			//separating first name and last name
			String names[] = name.split(" ");
			
			if(names[0].equals("Victoria") && names[1].equals("Terces")) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
