package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Framework.AbstractUtilities.AbstractUtilities;

public class EmployeeListPage extends AbstractUtilities {
	
	public WebDriver driver;
	
	public EmployeeListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Employee List ']")
	WebElement employeeList;
	
	@FindBy(id = "addEmployeeButton")
	WebElement addButton;
	
	public void goToEmployeeListPage() {
		waitForElementToBeClickable(employeeList);
		employeeList.click();
	}
	
	public AddEmployeeDetailsPage addEmployeeButton() {
		waitForElementToBeClickable(addButton);
		addButton.click();
		AddEmployeeDetailsPage addEmployeeDetailsPage = new AddEmployeeDetailsPage(driver);
		return addEmployeeDetailsPage;
	}

}
