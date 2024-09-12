package Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Framework.AbstractUtilities.AbstractUtilities;

public class EmployeeProfilePage extends AbstractUtilities {
	
	public WebDriver driver;

	public EmployeeProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	By empName = By.cssSelector(".emp-name");
	
	public String verifyNewEmployee() {
		waitForVisibilityOfElementLocated(empName);
		String actualName = driver.findElement(empName).getText();
		return actualName;
		
	}
	
}
