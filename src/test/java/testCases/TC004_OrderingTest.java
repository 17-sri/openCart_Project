
package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.Ordering;
import testBase.BaseClass;

public class TC004_OrderingTest extends BaseClass{
	@Test
	public void ordering() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		//LoginPage
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(properties.getProperty("email"));
		loginPage.setPassword(properties.getProperty("password"));
		loginPage.clickLogin();
		Ordering order = new Ordering(driver);
		order.searchProduct(properties.getProperty("searchiphone"));
		order.btnSearch();
		String itemText = order.itemName();
		Assert.assertEquals(itemText, properties.getProperty("searchiphone"));
		order.clickItem();
		order.btnAddCart();
		String cartConfirmMsg = order.getSuccesMsg();
		Assert.assertEquals(cartConfirmMsg, "Success: You have added "+properties.getProperty("searchiphone")+" to your shopping cart!"+"\n"+"×");
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOf(null));
		order.btnCart();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", order.clkCheckout());		
		order.clkCheckout().click();
		String cnfrmMsg = order.getConfirmationMsg();
		Assert.assertEquals(cnfrmMsg, "Products marked with *** are not available in the desired quantity or not in stock!"+"\n"+"×");
	}
}
