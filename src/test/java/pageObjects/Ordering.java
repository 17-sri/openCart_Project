package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Ordering extends BasePage{

	public Ordering(WebDriver driver) {
		super(driver);
	}
	//@FindBy(css = "a[href*='product/category&path=24']")
	//WebElement clkPhoneTab;
	@FindBy(css = "input[name='search']")
	WebElement txtSearch;
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement clkSearch;
	@FindBy(css = "div[class='caption'] h4 a")
	WebElement txtItem;
	@FindBy(xpath = "//img[@class='img-responsive']")
	WebElement clkItem;
	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement clkAddCart;
	@FindBy(css = "[class*='alert-dismissible']")
	WebElement successMsg;
	@FindBy(xpath = "//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	WebElement clkCart;
	@FindBy(xpath = "//strong[text()='View Cart']")
	WebElement viewCart;
	@FindBy(xpath = "//a[text()='Checkout']")
	WebElement btnCheckout;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement confirmMessage;
	public void searchProduct(String product) {
		txtSearch.sendKeys(product);
	}
	public void btnSearch() {
		clkSearch.click();
	}
	public String itemName() {
		try {
			return(txtItem.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
	public void clickItem() {
		clkItem.click();
	}
	public void btnAddCart() {
		clkAddCart.click();
	}
	public String getSuccesMsg() {
		waitForWebElementToAppear(successMsg);
		return successMsg.getText();
	}
	public void btnCart() {
		clkCart.click();
		viewCart.click();
	}
	public WebElement clkCheckout() {
		return btnCheckout;
	}
	public String getConfirmationMsg() {
		waitForWebElementToAppear(confirmMessage);
		try {
			return(confirmMessage.getText());
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
}
