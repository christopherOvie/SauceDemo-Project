package swagLab;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BasePage {

	public WebDriver driver;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
	}

	protected void visit(String appURL) {
		driver.get(appURL);
	}

	protected WebElement getElementWhenVisible(By elementToken) {
		WebElement foundElement = null;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			foundElement = wait.until(ExpectedConditions.elementToBeClickable(elementToken));
		} catch (NoSuchElementException excp) {
			fail(logMessage("[ASSERT FAILED]: Element " + elementToken + " not found on the webPage !!!"));
		} catch (NullPointerException npe) {
			fail("[UNHANDLED EXCEPTION]: " + npe.getLocalizedMessage());
		}
		return foundElement;
	}

	protected boolean clickOnElement(By elementToken) {
		try {
			WebElement el = getElementWhenVisible(elementToken);
			el.click();
			return true;
		} catch (NullPointerException npe) {
			fail("[UNHANDLED EXCEPTION]: " + npe.getLocalizedMessage());
		}
		return false;
	}

	protected void sendText(By elementToken, String text) {
		getElementWhenVisible(elementToken).sendKeys(text);
	}

	protected String logMessage(String message) {
		Reporter.log(message, true);
		return message;
	}

}
