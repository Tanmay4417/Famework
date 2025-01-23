package com.comcast.crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listnerutility.ListnerImpClass;
import com.comcast.crm.objectrepositary.utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositary.utility.HomePage;
import com.comcast.crm.objectrepositary.utility.LoginPage;
import com.comcast.crm.objectrepositary.utility.OrganizationInfoPage;
import com.comcast.crm.objectrepositary.utility.OrganizationsPage;

@Listeners(com.comcast.crm.listnerutility.ListnerImpClass.class)
public class CreateOrgTestUsingBaseClassTest extends BaseClass {

	@Test(groups = "smokeTest")
	
	public void createOrgTest() throws Throwable  
	
	{
		// ListnerImpClass.test.log(Status.INFO, "read data from excel");
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgname = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		// Step 1: login to app
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// step 2: navigate to org module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org page");

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step 3: click on create org button
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create org page");

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		// step 4: enter all details and create new org
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org");

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgname);
		UtilityClassObject.getTest().log(Status.INFO, orgname + "===>Create a new org");

		// step 5: verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualOrgName = oip.getHeaderMsg().getText();
		if (actualOrgName.contains(orgname)) {
			System.out.println(orgname + " is verified==PASS");
		} else {
			System.out.println(orgname + "is not verified==FAIL");
		}

	}

	

	

}
