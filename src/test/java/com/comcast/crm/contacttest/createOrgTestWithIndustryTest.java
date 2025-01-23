package com.comcast.crm.contacttest;

import java.time.Duration;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositary.utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositary.utility.HomePage;
import com.comcast.crm.objectrepositary.utility.OrganizationInfoPage;
import com.comcast.crm.objectrepositary.utility.OrganizationsPage;

public class createOrgTestWithIndustryTest extends BaseClass {
	
	@Test(groups = "regressionTest")
	public void createOrgTestWithIndustry() throws Throwable {
		String orgname = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String IndustryName = eLib.getDataFromExcel("org", 4, 3);
		String TypeName = eLib.getDataFromExcel("org", 4, 4);

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
		cnop.createOrg(orgname, IndustryName);

		// verify the industry and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualOrgName = oip.getHeaderMsg().getText();
		if (actualOrgName.contains(orgname)) {
			System.out.println(orgname + " is verified==PASS");
		} else {
			System.out.println(orgname + "is not verified==FAIL");
		}
	}

}
