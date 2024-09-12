package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id = "txtUsername")
	WebElement userName;
	
	@FindBy(id = "txtPassword")
	WebElement passWord;
	
	@FindBy(css = "button[type='submit']")
	WebElement submit;
	
	public EmployeeListPage loginApplication(String username, String password) {
		userName.sendKeys(username);
		passWord.sendKeys(password);
		submit.click();
		EmployeeListPage employeeListPage = new EmployeeListPage(driver);
		return employeeListPage;
		
	}
	
	public void goTo() {
		driver.get("https://harshademo-trials714.orangehrmlive.com/auth/seamlessLogin");
	}

}
