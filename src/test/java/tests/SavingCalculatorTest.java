package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SavingCalculatorTest {

    WebDriver driver;

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

    @Test
    public void itShouldDisplayTitle() throws InterruptedException {
        //driver.findElement(By.cssSelector("h1")).getText();
        Assert.assertEquals("Savings Calculator", driver.findElement(By.cssSelector("h1")).getText());
        Thread.sleep(5000);
    }

    @Test
    public void itShouldDisableApplyButtonOnPageOpen() {
        //driver.findElement(By.cssSelector("button.btn-block")).isEnabled();
        Assert.assertFalse(driver.findElement(By.cssSelector("button.btn-block")).isEnabled());
    }

    @Test
    public void itShouldEnableApplyButton() {
        // vybrat fond
        Select option = new Select(driver.findElement(By.id("fundSelect")));
        option.selectByVisibleText("Hoggwart's Fund");
        // zadat sumu
        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys("3200");
        // zadat pocet rokov
        driver.findElement(By.id("yearsInput")).sendKeys("12");
        // zadar email
        driver.findElement(By.id("emailInput")).sendKeys("volar@morgulis.si");
        // overit button
        Assert.assertTrue(driver.findElement(By.cssSelector("button.btn-block")).isEnabled());
    }

    @Test
    public void itShouldNotSelectAnyFundOnPageOpen() {
        String selectedOption = new Select(driver.findElement(By.id("fundSelect"))).getFirstSelectedOption().getText();
        System.out.println(selectedOption);
        Assert.assertEquals("Select your fund", selectedOption);
    }

}
