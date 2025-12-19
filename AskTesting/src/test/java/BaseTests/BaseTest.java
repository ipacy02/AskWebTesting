package BaseTests;

import org.example.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://askomdch.com/");
        homePage = new HomePage(driver);
    }

}
