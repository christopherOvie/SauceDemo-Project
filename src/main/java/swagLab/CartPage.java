package swagLab;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class CartPage extends BasePage {

	public static By cartButton = By.className("shopping_cart_link");
	public static By checkoutButton = By.id("checkout");
	public static By firstName = By.id("first-name");
	public static By lastName = By.id("last-name");
	public static By postalCode = By.id("postal-code");
	public static By continueButtonOncheckout = By.id("continue");
	public static By itemTotal = By.className("summary_subtotal_label");
	public static By tax = By.className("summary_tax_label");
	public static By totalPriceAfterTax = By.className("summary_total_label");
	
	List<String> priceOfAllItems = new ArrayList<String>();
	
	protected CartPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickOnCartButton() {
		clickOnElement(cartButton);
		Reporter.log("Clicked on Cart button");
	}
	
	public void clickOnCheckout() {
		clickOnElement(checkoutButton);
		Reporter.log("Clicked on Checkout button");
	}
	
	public void enterPersonalInformation (String firstName,String lastName, String postalCode) {
		sendText(CartPage.firstName, firstName);
		sendText(CartPage.lastName, lastName);
		sendText(CartPage.postalCode, postalCode);
		Reporter.log("Entered user information ");
	}
	
	public void clickContinueBtnOnCheckoutPage() {
		clickOnElement(continueButtonOncheckout);
		Reporter.log("Clicked on Continue button on checkout page");
	}
	
	public void getPriceOfEachItem() {
		List<WebElement> webElementsOfAllItems = driver.findElements(By.className("inventory_item_price"));
		for (WebElement priceOfOneItem : webElementsOfAllItems)
		{
			priceOfAllItems.add(priceOfOneItem.getText().replace("$",""));
		}
	}
	
	public String addAllPrices() {
		double priceOfOneItemFromList;
		double totalPrice = 0;
		for(int i=0;i<= priceOfAllItems.size()-1; i++) {
			priceOfOneItemFromList = Double.valueOf(priceOfAllItems.get(i));
			totalPrice += priceOfOneItemFromList;	 
		}
		return String.valueOf(totalPrice);
	}
	
	public void verifyItemTotalWithoutTax(String itemTotalText) {
		String itemTotalTextFromWeb = getElementWhenVisible(itemTotal).getText().replace("Item total: $","");
		Assert.assertTrue(itemTotalTextFromWeb.equals(itemTotalText));
		Reporter.log("Verified Items Total price without tax");
	}
	
	public void verifyTotalPriceWithTax(String itemTotalText) {
		String taxPrice = driver.findElement(tax).getText().replace("Tax: $","");
		double totalPrice = Double.valueOf(itemTotalText) + Double.valueOf(taxPrice);
		String totalPriceFromWeb = getElementWhenVisible(totalPriceAfterTax).getText().replace("Total: $","");
		Assert.assertTrue(totalPrice == (Double.valueOf(totalPriceFromWeb)));
		Reporter.log("Verified total price including tax");
	}
	
	
	
}
