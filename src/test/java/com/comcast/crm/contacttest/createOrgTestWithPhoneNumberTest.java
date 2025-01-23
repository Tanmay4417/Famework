package com.comcast.crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositary.utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositary.utility.HomePage;
import com.comcast.crm.objectrepositary.utility.OrganizationsPage;

public class createOrgTestWithPhoneNumberTest extends BaseClass {
	
	@Test(groups ="regressionTest")
	public void createOrgTestWithPhoneNumber() throws Throwable {
		String orgname = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		// step 2: navigate to org module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// step 3: click on create org button
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		// step 4: enter all details and create new org
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgwithPhno(orgname, phoneNumber);

		// step 5: verify phno as expected result
		String actPhoneNo = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNo.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is verified=PASS");
		} else {
			System.out.println(phoneNumber + "is not verified==FAIL");
		}
	}

}
