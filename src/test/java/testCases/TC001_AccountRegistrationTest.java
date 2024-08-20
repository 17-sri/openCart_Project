package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test
	public void verify_account_registration() {
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickRegister();
		
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLasrName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		regPage.setTelephone(randomNumber());
		String password =  randomAlphaNumeric();
		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		regPage.setPrivacyPolicy();
		regPage.clickContinue();
		String confirmationMsg = regPage.getConfirmationMsg();
		Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
	}
} 
