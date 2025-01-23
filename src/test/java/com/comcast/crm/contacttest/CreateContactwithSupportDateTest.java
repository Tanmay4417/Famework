package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositary.utility.ContactsPage;
import com.comcast.crm.objectrepositary.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepositary.utility.HomePage;

/**
 * @author Tanmay
 * 
 */

public class CreateContactwithSupportDateTest extends BaseClass {
	
	@Test(groups = "regressionTest")
	public void CreateContactwithSupportDateTest() throws Throwable {
		/* read testScript data from Excel file */
		String lastname = eLib.getDataFromExcel("contact", 4, 3) + jLib.getRandomNumber();

		/* step 2: navigate to org module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step 3: click on create org button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step 4: enter all details and create new org */
		String StartDate = jLib.getSystemDateYYYYDDMM();
		String afterDateRequires = jLib.getRequiredDateYYYYDDMM(30);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContactWithSupportDate(lastname, StartDate, afterDateRequires);

		/* step 5: verify header msg expected result */
		String actualStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
//		if (actualStartDate.contains(StartDate)) {
//			System.out.println(StartDate + " is verified==PASS");
//		} else {
//			System.out.println(StartDate + "is not verified==FAIL");
//		}
		boolean status1= actualStartDate.contains(StartDate);
		Assert.assertTrue(status1);
		

		/* step 6: verify header orgname info expected result */
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
//		if (actEndDate.equals(afterDateRequires)) {
//			System.out.println(afterDateRequires + " is verified==PASS");
//		} else {
//			System.out.println(afterDateRequires + "is not verified==FAIL");
//		}
		Assert.assertEquals(actEndDate, afterDateRequires);
	}

}
