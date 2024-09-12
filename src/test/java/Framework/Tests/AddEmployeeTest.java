package Framework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.PageObjects.AddEmployeeDetailsPage;
import Framework.PageObjects.EmployeeListPage;
import Framework.PageObjects.EmployeeProfilePage;
import Framework.PageObjects.RemainingFormDetails;
import Framework.TestComponents.CommonBaseTest;
import Framework.TestComponents.Retry;

public class AddEmployeeTest extends CommonBaseTest {
	
	@Test(dataProvider = "readData", retryAnalyzer = Retry.class)
	public void addAndVerifyEmployee(HashMap<String, String> input) throws InterruptedException {
		
		String expectedName = input.get("firstName")+" "+input.get("lastName");
		
		EmployeeListPage employeeListPage = landingPage.loginApplication(input.get("username"),input.get("password"));
		employeeListPage.goToEmployeeListPage();
		AddEmployeeDetailsPage addEmployeeDetailsPage = employeeListPage.addEmployeeButton();
		addEmployeeDetailsPage.enterNameDetails(input.get("firstName"),input.get("lastName"));
		addEmployeeDetailsPage.selectJoiningDate(input.get("year"),input.get("month"),input.get("day"));
		addEmployeeDetailsPage.selectLocation(input.get("location"));
		RemainingFormDetails remainingFormDetails = addEmployeeDetailsPage.saveDetails();
		EmployeeProfilePage employeeProfilePage = remainingFormDetails.submitForm();
		String successMessage = remainingFormDetails.confirmationMessage();
		System.out.println(successMessage);
		
		//Test is deliberately failed so that screenshot can be captured in Reports Folder
		Assert.assertEquals(successMessage, "Successfuly Saved");
		String actualName = employeeProfilePage.verifyNewEmployee();
		System.out.println(actualName);
		Assert.assertEquals(actualName, expectedName);
		
	}
	
	@DataProvider
	public Object[][] readData() throws IOException {
		
		List<HashMap<String, String>> data = readJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Framework//Data//NewEmployeeData.json");
		return new Object[][] {{data.get(0)}};
		
	}
	
	
}
