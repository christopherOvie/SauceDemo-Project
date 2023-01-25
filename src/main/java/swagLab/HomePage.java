package swagLab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import utilities.PropFileHandler;

public class HomePage extends BasePage {
	public static By addToCartFirstButton = By.xpath("(//button[contains(@id,\"add-to-cart\")])[1]");
	public static By addToCartSecondButton = By.xpath("(//button[contains(@id,\"add-to-cart\")])[2]");
	public static By productImages = By.cssSelector("img[src=\"/static/media/sl-404.168b1cce.jpg\"]");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void launchApplication() {
		visit(PropFileHandler.readProperty("appURL"));
		Reporter.log("Launched the Application URL");
	}
	public void addTwoItemsToCart() {
		clickOnElement(addToCartFirstButton);
		clickOnElement(addToCartSecondButton);
		Reporter.log("Added two items to cart");
	}
	
	public void verifyProductImageDuplicacy() {
		Assert.assertTrue(driver.findElements(productImages).size()>1);
		Reporter.log("Same images are displayes for more than one product");
	}
}
