package com.comcast.crm.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositary.utility.ContactsPage;
import com.comcast.crm.objectrepositary.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepositary.utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositary.utility.HomePage;
import com.comcast.crm.objectrepositary.utility.OrganizationsPage;
import com.comcast.crm.objectrepositary.utility.OrganizationsSelectPage;

/**
 * @author Tanmay
 * 
 */

public class CreateContactwithOrgTest extends BaseClass{
	
	@Test(groups = "regressionTest")
	public void CreateContactwithOrgTest() throws Throwable {
		String lastname = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();
		String orgname = eLib.getDataFromExcel("contact", 7, 4) + jLib.getRandomNumber();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		/* step 2: navigate to org module*/
		HomePage hp1 = new HomePage(driver);
		hp1.getOrgLink().click();

		/* step 3: click on create org button */
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		/* step 4: enter all details and create new org */
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgname);

		/* step 5: verify header msg expected result */
		String headerInfoo = driver.findElement(By.className("dvHeaderText")).getText();
		
		if (headerInfoo.contains(orgname)) {
			System.out.println(orgname + " is created==PASS");
		} else {
			System.out.println(orgname + "is not created==FAIL");
		}

		// step 2: navigate to org module 
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step 3: click on create org button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step 4: enter all details and create new contact */
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.getLastnameEdt().sendKeys(lastname);
		cncp.getOrgImgSelectButton().click();

		/* switch to child window */

		String parent = driver.getWindowHandle();
		wLib.switchNewBrowserTab(driver, "Accounts&action");
		OrganizationsSelectPage osp=new OrganizationsSelectPage(driver);

		osp.selectOrg(orgname);
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		driver.switchTo().window(parent);
		cncp.getSaveBtn().click();

		/* step 5: verify header msg expected result */
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if (headerInfo.contains(lastname)) {
//			System.out.println(lastname + " is created==PASS");
//		} else {
//			System.out.println(lastname + "is not created==FAIL");
//		}
		boolean status2= headerInfo.contains(lastname);
		Assert.assertTrue(status2);

		/* step 6: verify header orgname info expected result */
		String actOrgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgname);
//		if (actOrgname.trim().equals(orgname)) {
//			System.out.println(orgname + " is verified==PASS");
//		} else {
//			System.out.println(orgname + "is not verified==FAIL");
//		}
		boolean status3= actOrgname.contains(orgname);
		Assert.assertTrue(status3);
	
		

	}

}
