package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("starting test case TC001_AccountRegistrationTest...");
		try {

			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("clicked on myAccount link");
			homePage.clickRegister();
			logger.info("clicked on Register link");
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("providing customer details");
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLasrName(randomString().toUpperCase());
			regPage.setEmail(randomString()+"@gmail.com"); // randomly generated email
			regPage.setTelephone(randomNumber());
			String password =  randomAlphaNumeric();
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			regPage.setPrivacyPolicy();
			regPage.clickContinue();
			logger.info("validating expected message");
			String confirmationMsg = regPage.getConfirmationMsg();
			Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("test got failed");
			logger.debug("DeBug logs....");
			Assert.fail();
		}
		logger.info("test finished ---->>>   TC001_AccountRegistrationTest");
	}
} 
