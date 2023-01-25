package swagLab;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

	public static By userNameField = By.id("user-name");
	public static By passwordField = By.id("password");
	public static By loginButton = By.id("login-button");
	public static By errorMsg = By.cssSelector("h3[data-test='error']");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void login(String username,String password)
	{
		sendText(userNameField,username);
		sendText(passwordField,password);
		clickOnElement(loginButton);
	}

	public String getErrorMsg() {
		 return getElementWhenVisible(errorMsg).getText();
	}
}
