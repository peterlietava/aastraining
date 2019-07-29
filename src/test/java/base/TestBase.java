package base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    protected WebDriver driver;

    @Before
    public void setUp() {
        System.getProperty("Webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8888/savingscalculator.php");
    }

    @After
    public void tearDown() {
        driver.close();
        //driver.quit();
    }
}
