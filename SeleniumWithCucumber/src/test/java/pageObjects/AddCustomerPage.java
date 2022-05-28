package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver wd;
	
	public AddCustomerPage(WebDriver wd1)
	{
		 wd = wd1;
		//initElements is a static method in Page Factory class. Using the initElements method, 
		//one can initialize all the web elements located by @FindBy annotation
		PageFactory.initElements(wd, this);
	}
	
	By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(), 'Customers')]");
	By lnkCustomers_menuitem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(), 'Customers')]");
	
	//add new button
	By btnAddnew = By.xpath("//a[@class='btn btn-primary']");
	
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	
	By firstName = By.xpath("//input[@id='FirstName']");
	By lastName = By.xpath("//input[@id='LastName']");
	
	By rdMaleGender = By.xpath("//input[@id='Gender_Male']");
	By rdFemaleGender = By.xpath("//input[@id='Gender_Female']");
	
	By txtDob = By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName = By.xpath("//input[@id='Company']");
	
	By txtCustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	By listItemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By listItemRegistered = By.xpath("//li[@id='807f0a52-ca30-4d9d-acf6-ad36ffb72beb']");
	By listItemGuests = By.xpath("//li[contains(text(),'Guests')]");
	By listItemVendors = By.xpath("//li[contains(text(),'Vendors')]");
	By listItemForumModrators = By.xpath("//li[contains(text(),'Forum Moderators')]");
	
	By MgrVendor = By.xpath("//*[@id='VendorId']"); 
	By txtAdminComment = By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
	//Action methods implementation
	
	public String getPageTitle()
	{
		return wd.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		wd.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		wd.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNew() {
		wd.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email) {
		wd.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		wd.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException
	{
		if(!role.equals("Vendors"))  //If role is Vendors should not delete Register as per req.
		{
			wd.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		}
		wd.findElement(txtCustomerRoles).click();
	
	    WebElement listItem;
	    Thread.sleep(3000);
	    
	    if(role.equals("Administrators")) {
	    	listItem = wd.findElement(listItemAdministrators);
	    }
	    else if(role.equals("Guests")) {
	    	listItem = wd.findElement(listItemGuests);
	    }
	    else if(role.equals("Registered")) {
	    	listItem = wd.findElement(listItemRegistered);
	    }
	    else if(role.equals("Vendors")) {
	    	listItem = wd.findElement(listItemVendors);
	    }
	    else {
	    	listItem = wd.findElement(listItemGuests);
	    }
	    
	    //listItem.click();
	    
	    //if click() method doesn't work then use JavaScriptExecutor
	    JavascriptExecutor jse = (JavascriptExecutor) wd;
	    jse.executeScript("arguments[0].click();", listItem);
	}
	
	public void setManagerofVendor(String value)
	{
		Select drp = new Select(wd.findElement(MgrVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male")) {
			wd.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female")) {
			wd.findElement(rdFemaleGender).click();
		}
		else {
			wd.findElement(rdMaleGender).click(); //by default
		}
	}
	
	public void setFirstName(String fname)
	{
		wd.findElement(firstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		wd.findElement(lastName).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		wd.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comName)
	{
		wd.findElement(txtCompanyName).sendKeys(comName);
	}
	
	public void setAdminContent(String content)
	{
		wd.findElement(txtAdminComment).sendKeys(content);
	}
	
	public void clickOnSave()
	{
		wd.findElement(btnSave).click();
	}
	  
}

