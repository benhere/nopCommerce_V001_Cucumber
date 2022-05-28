package utilities;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelpher {
	
	public WebDriver wd;
	
	public WaitHelpher(WebDriver wd) {
		this.wd = wd;
	}
	
	//wait for WebElement till it is visible on the web page
	public void waitForWebElement(WebElement we, long timeOutInSeconds)
	{
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(timeOutInSeconds));
		//since Explicit wait is condition based(not time based), so we need to pass ExpectedCondition
		wait.until(ExpectedConditions.visibilityOf(we));
	}
}
