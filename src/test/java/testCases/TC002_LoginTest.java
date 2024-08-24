package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups = {"Sanity", "Master"})
	public void verify_login() {

		logger.info("...starting TC002_LoginTest... ");
		try {
			//HomePage
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();
			//LoginPage
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(properties.getProperty("email"));
			loginPage.setPassword(properties.getProperty("password"));
			loginPage.clickLogin();
			//MyAccountPage
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExists();
			Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true, "Login Failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("...finished TC002_LoginTest... ");
	}
}
