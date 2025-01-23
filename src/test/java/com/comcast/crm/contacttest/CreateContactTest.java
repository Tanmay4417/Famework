package com.comcast.crm.contacttest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositary.utility.ContactsInfoPage;
import com.comcast.crm.objectrepositary.utility.ContactsPage;
import com.comcast.crm.objectrepositary.utility.CreatingNewContactPage;
import com.comcast.crm.objectrepositary.utility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositary.utility.HomePage;
import com.comcast.crm.objectrepositary.utility.LoginPage;
import com.comcast.crm.objectrepositary.utility.OrganizationsPage;
import com.comcast.crm.objectrepositary.utility.OrganizationsSelectPage;
/**
 * @author Tanmay
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups ="smokeTest")
	public void CreateContactTestUsingBaseClas() throws Throwable {
		/* read testScript data from Excel file*/
		String lastname = eLib.getDataFromExcel("contact", 1, 3) + jLib.getRandomNumber();

		/* step 2: navigate to org module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step 3: click on create org button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step 4: enter all details and create new  org */
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createContact(lastname);

		/* step 6: verify header lastname info expected result */
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String headerInfo =cip.getHeaderInfo().getText();  //driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean status= headerInfo.contains(lastname);
		Assert.assertTrue(status);
		
		String actLastName = cip.getActLastName().getText();  // driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName, lastname);
		soft.assertAll();
	}

	

	

}
