package Framework.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractUtilities.AbstractUtilities;

public class AddEmployeeDetailsPage extends AbstractUtilities {
	
	public WebDriver driver;

	public AddEmployeeDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "first-name-box")
	WebElement firstNameEle;
	
	@FindBy(id = "last-name-box")
	WebElement lastNameEle;
	
	@FindBy(css = ".btn.date-picker-button")
	WebElement calendarButton;
	
	@FindBy(css = ".dropdown.bootstrap-select.picker__select--year")
	WebElement yearButton;
	
	@FindBy(css = ".dropdown.bootstrap-select.picker__select--month")
	WebElement monthButton;
	
	@FindBy(id = "location")
	WebElement locationEle;
	
	@FindBy(css = "div.custom-dropdown-item-inner-container span")
	List<WebElement> options;
	
	@FindBy(id = "modal-save-button")
	WebElement saveButton;
	
	public void enterNameDetails(String firstName, String lastName) {
		waitForElementToBeClickable(firstNameEle);
		firstNameEle.sendKeys(firstName);
		lastNameEle.sendKeys(lastName);
		
	}
	
	public void selectJoiningDate(String year, String month, String day) {
		calendarButton.click();
		yearButton.click();
		driver.findElement(By.xpath("//a[@class='dropdown-item']/span[text()='"+year+"']")).click();
		monthButton.click();
		driver.findElement(By.xpath("//a[@class='dropdown-item']/span[text()='"+month+"']")).click();
		driver.findElement(By.xpath("//table[@class='picker__table']/tbody/tr/td/div[text()='"+day+"']")).click();
		
	}
	
	public void selectLocation(String location) {
		locationEle.click();
		try {
			WebElement matchedOption = options.stream().filter(option -> option.getText().equalsIgnoreCase(location)).findFirst().orElse(null);
			matchedOption.click();
		}
		catch (StaleElementReferenceException e) {
			e.toString();
		}
		
	}
	
	public RemainingFormDetails saveDetails() {
		waitForElementToBeClickable(saveButton);
		saveButton.click();
		RemainingFormDetails remainingFormDetails = new RemainingFormDetails(driver);
		return remainingFormDetails;
		
	}
	
	

}
