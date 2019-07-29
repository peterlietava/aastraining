package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CalculationTest {
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

    public void insertValues(String fund, String oneTimeInvestment, String years, String email) {
        // vybrat fond
        Select option = new Select(driver.findElement(By.id("fundSelect")));
        option.selectByVisibleText(fund);
        // zadat sumu
        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys(oneTimeInvestment);
        // zadat pocet rokov
        driver.findElement(By.id("yearsInput")).sendKeys(years);
        // zadar email
        driver.findElement(By.id("emailInput")).sendKeys(email);
        // overit button
        Assert.assertTrue(driver.findElement(By.cssSelector("button.btn-block")).isEnabled());
    }

    @Test
    public void itShouldCalculateTotalIncome() {
       // 1. vybrat fond, zadat sumu, roky, email
        insertValues("Hoggwart's Fund", "3200", "12", "volar@morgulis.si");
        // overit ze totalIncome nie je prazdny
        System.out.println(driver.findElement(By.cssSelector("div.result > div:nth-child(1) > p")).getText());
        Assert.assertFalse(driver.findElement(By.cssSelector("div.result > div:nth-child(1) > p")).getText().isEmpty());

    }

    @Test
    public void itShouldCalculateInterestIncome() {
        // 1. vybrat fond, zadat sumu, roky, email
        insertValues("Hoggwart's Fund", "3200", "12", "volar@morgulis.si");
        // overit ze totalIncome nie je prazdny
        System.out.println(driver.findElement(By.cssSelector("div.result > div:nth-child(2) > p")).getText());
        Assert.assertFalse(driver.findElement(By.cssSelector("div.result > div:nth-child(2) > p")).getText().isEmpty());
    }

    @Test
    public void itShouldCalculateRiskIncome() {
        // 1. vybrat fond, zadat sumu, roky, email
        insertValues("Hoggwart's Fund", "3200", "12", "volar@morgulis.si");
        // overit ze totalIncome nie je prazdny
        System.out.println(driver.findElement(By.cssSelector("div.result > div:nth-child(3) > p")).getText());
        Assert.assertFalse(driver.findElement(By.cssSelector("div.result > div:nth-child(3) > p")).getText().isEmpty());
    }
}
