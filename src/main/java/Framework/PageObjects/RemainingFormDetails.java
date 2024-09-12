package Framework.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.AbstractUtilities.AbstractUtilities;

public class RemainingFormDetails extends AbstractUtilities {
	
	public WebDriver driver;

	public RemainingFormDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@class='btn btn-secondary right']")
	WebElement submitButton;
	
	By toastMessage = By.cssSelector(".toast-message");
	
	public EmployeeProfilePage submitForm() throws InterruptedException {
		for(int i=1;i<=3;i++)
		{
			Thread.sleep(5000);
			submitButton.click();
			
		}
		
		EmployeeProfilePage employeeProfilePage = new EmployeeProfilePage(driver);
		return employeeProfilePage;
		
	}
	
	public String confirmationMessage() {
		waitForVisibilityOfElementLocated(toastMessage);
		String successMessage = driver.findElement(By.cssSelector(".toast-message")).getText();
		return successMessage;
		
	}
	
	

}
