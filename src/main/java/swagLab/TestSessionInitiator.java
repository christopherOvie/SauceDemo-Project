package swagLab;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.PropFileHandler;

public class TestSessionInitiator {

	public WebDriver driver;
	private String browser;

	// Defining classes Variables
	public static HomePage homepage;
	public static LoginPage loginPage;
	public static CartPage cartPage;

	public TestSessionInitiator(String browser) {
		setBrowser(browser);
		initialize(getBrowser());
		initClasses(driver);
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return this.browser;
	}

	public void initialize(String browserName) {
		switch (browserName.toLowerCase()) {
			case "chrome":
			case "ch":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
			case "ff":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "internetexplorer":
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser passed in: " + browser);
				break;
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropFileHandler.readProperty("timeout")),
				TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void initClasses(WebDriver driver) {
		homepage = new HomePage(driver);
		loginPage = new LoginPage(driver);
		cartPage = new CartPage(driver);
	}

	public void quit() {
		Reporter.log("Exiting browser");
		driver.quit();
	}

}
