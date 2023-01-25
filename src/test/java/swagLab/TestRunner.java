package swagLab;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilities.PropFileHandler;

public class TestRunner {

	TestSessionInitiator test;
	

	@BeforeMethod
	public void launchApplication()  {
		test = new TestSessionInitiator(PropFileHandler.readProperty("browser"));
		TestSessionInitiator.homepage.launchApplication();
	}

	@Test
	public void checkoutAnItem() {
		TestSessionInitiator.loginPage.login(PropFileHandler.readProperty("standardUser"),
				PropFileHandler.readProperty("password"));
		Reporter.log("Logged in as Standard User");
		eCommFlow();
	}
	
	@Test
	public void checkLockeduser() {
		TestSessionInitiator.loginPage.login(PropFileHandler.readProperty("lockedUser"),
				PropFileHandler.readProperty("password"));
		Reporter.log("Trying to login as Locked User");
		Assert.assertTrue(TestSessionInitiator.loginPage.getErrorMsg().equals("Epic sadface: Sorry, this user has been locked out."),"Error message not matched");
	}
	
	@Test
	public void checkProblemUser() {
		TestSessionInitiator.loginPage.login(PropFileHandler.readProperty("problemUser"),
				PropFileHandler.readProperty("password"));
		Reporter.log("Logged in as Problem User");
		TestSessionInitiator.homepage.verifyProductImageDuplicacy();
	}
	
	@Test
	public void checkPerformanceGlitchUser() {
		TestSessionInitiator.loginPage.login(PropFileHandler.readProperty("performanceGlitchUser"),
				PropFileHandler.readProperty("password"));
		Reporter.log("Logged in as Performance Glitch User");
		eCommFlow();
	}
	
	public void eCommFlow() {
		String firstName = PropFileHandler.readProperty("firstName");
		String lastName = PropFileHandler.readProperty("lastName");
		String postalCode = PropFileHandler.readProperty("postalCode");
		
		TestSessionInitiator.homepage.addTwoItemsToCart();
		TestSessionInitiator.cartPage.clickOnCartButton();
		TestSessionInitiator.cartPage.clickOnCheckout();
		TestSessionInitiator.cartPage.enterPersonalInformation(firstName,lastName,postalCode);
		TestSessionInitiator.cartPage.clickContinueBtnOnCheckoutPage();
		TestSessionInitiator.cartPage.getPriceOfEachItem();
		String itemTotalPrice = TestSessionInitiator.cartPage.addAllPrices();
		TestSessionInitiator.cartPage.verifyItemTotalWithoutTax(itemTotalPrice);
		TestSessionInitiator.cartPage.verifyTotalPriceWithTax(itemTotalPrice);
	}
	
	@AfterMethod
	public void closeSession() {
		test.quit();
	}

}
