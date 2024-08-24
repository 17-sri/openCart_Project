package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven") // because DataProvider ="LoginData" is located in different package and different class, that's why we should specify name of the class "DataProvider.class"
	public void verify_loginDDT(String email, String pwd, String exp) {
		logger.info("...starting TC003_LoginDDT... ");
		try {
			//HomePage
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();
			//LoginPage
			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmail(email);
			loginPage.setPassword(pwd);
			loginPage.clickLogin();
			//MyAccountPage
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountPageExists();
			//Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true, "Login Failed");
			/*
			 * Data is valid   -  login success - test pass    -  logout
			 *                 -  login failed  - test failed
			 * Data is Invalid -  login success - test failed  -  logout
			 *                 -  login failed  - test pass             
			 */
			if(exp.equalsIgnoreCase("Valid")) {
				if(targetPage == true) {
					Assert.assertTrue(true);
					myAcc.clickLogout();
				}
				else {
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("Invalid")) {
				if(targetPage == true) {
					Assert.assertTrue(false);
					myAcc.clickLogout();
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("...finished TC003_LoginDDT...");
	}
}



