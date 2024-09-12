package Framework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EmployeeInformationUpdate {

	public static void main(String[] args) throws InterruptedException {
		
		// Initial Raw Script
		
		String username = "Admin";
		String password = "af2T@l1SKH";
		String firstName = "Auto";
		String lastName = "Charly";
		String year = "2018";
		String month = "July";
		String day = "20";
		String location = "India Office";
		String expectedName = firstName+" "+lastName;
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mayank\\GridDownloads\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://harshademo-trials714.orangehrmlive.com/");
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Employee List ']")));
		driver.findElement(By.xpath("//a[text()='Employee List ']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addEmployeeButton")));
		driver.findElement(By.id("addEmployeeButton")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("first-name-box")));
		driver.findElement(By.id("first-name-box")).sendKeys(firstName);
		driver.findElement(By.id("last-name-box")).sendKeys(lastName);
		
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='custom-control custom-switch'])[1]")));
		boolean radioButton1 = driver.findElement(By.xpath("(//div[@class='custom-control custom-switch'])[1]")).isSelected();
		System.out.println(radioButton1);
		if(radioButton1==false)
		{
			driver.findElement(By.xpath("(//div[@class='custom-control custom-switch'])[1]")).click();
		}
		else
		{
			Assert.assertTrue(radioButton1);
		}
		
		//Handling Calender
		driver.findElement(By.cssSelector(".btn.date-picker-button")).click();
		driver.findElement(By.cssSelector(".dropdown.bootstrap-select.picker__select--year")).click();
		driver.findElement(By.xpath("//a[@class='dropdown-item']/span[text()='"+year+"']")).click();
		driver.findElement(By.cssSelector(".dropdown.bootstrap-select.picker__select--month")).click();
		driver.findElement(By.xpath("//a[@class='dropdown-item']/span[text()='"+month+"']")).click();
		driver.findElement(By.xpath("//table[@class='picker__table']/tbody/tr/td/div[text()='"+day+"']")).click();
		
		driver.findElement(By.id("location")).click();
		
		List<WebElement> options = driver.findElements(By.cssSelector("div.custom-dropdown-item-inner-container span"));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.custom-dropdown-item-inner-container span")));
		for (WebElement option : options) {
			try {
				if(option.getText().equalsIgnoreCase(location))
				{
					option.click();
				}
			}
			catch(StaleElementReferenceException e)
			{
				e.toString();
				//System.out.println(e.getMessage());
			}
			
		}
		
		boolean radioButton2 = driver.findElement(By.xpath("(//div[@class='custom-control custom-switch'])[2]")).isSelected();
		System.out.println(radioButton2);
		if(radioButton2==false)
		{
			Assert.assertFalse(radioButton2);
		}
		else
		{
			driver.findElement(By.xpath("(//div[@class='custom-control custom-switch'])[2]")).click();

		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("modal-save-button")));
		driver.findElement(By.id("modal-save-button")).click();
		
		for(int i=1;i<=3;i++)
		{
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@class='btn btn-secondary right']")).click();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
		String toastMessage = driver.findElement(By.cssSelector(".toast-message")).getText();
		System.out.println(toastMessage);
		Assert.assertEquals(toastMessage, "Successfully Saved");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".emp-name")));
		String actualName = driver.findElement(By.cssSelector(".emp-name")).getText();
		System.out.println(actualName);
		Assert.assertEquals(actualName, expectedName);
		
		driver.close();
		
	
	}

}
