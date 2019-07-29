package tests;

import base.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CalculationTest extends TestBase {
    /*
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
     */

    private void insertValues(String fund, String oneTimeInvestment, String years, String email) {
        // vybrat fond
        Select option = new Select(driver.findElement(By.id("fundSelect")));
        option.selectByVisibleText(fund);
        // zadat sumu
        driver.findElement(By.id("oneTimeInvestmentInput")).clear();
        driver.findElement(By.id("oneTimeInvestmentInput")).sendKeys(oneTimeInvestment);
        // zadat pocet rokov
        driver.findElement(By.id("yearsInput")).clear();
        driver.findElement(By.id("yearsInput")).sendKeys(years);
        // zadar email
        driver.findElement(By.id("emailInput")).clear();
        driver.findElement(By.id("emailInput")).sendKeys(email);
        // overit button
        //Assert.assertTrue(driver.findElement(By.cssSelector("button.btn-block")).isEnabled());
    }

    private String getTotalIncome() {
        WebElement totalIncome = driver.findElement(By.cssSelector("div.result > div:nth-child(1) > p"));
        return totalIncome.getText();
    }
    private String getInterestIncome() {
        WebElement totalIncome = driver.findElement(By.cssSelector("div.result > div:nth-child(2) > p"));
        return totalIncome.getText();
    }

    private String getRisk() {
        WebElement totalIncome = driver.findElement(By.cssSelector("div.result > div:nth-child(3) > p"));
        return totalIncome.getText();
    }
    @Test
    public void itShouldCalculateTotalIncome() {
       // 1. vybrat fond, zadat sumu, roky, email
        insertValues("Hoggwart's Fund", "3200", "12", "volar@morgulis.si");
        // overit ze totalIncome nie je prazdny
        WebElement total = driver.findElement(By.cssSelector("div.result > div:nth-child(1) > p"));
        System.out.println("TOTAL: " + total.getText());
        Assert.assertFalse(total.getText().isEmpty());
        Assert.assertTrue(total.getText().contains("kr"));
    }

    @Test
    public void itShouldCalculateInterestIncome() {
        // 1. vybrat fond, zadat sumu, roky, email
        insertValues("Hoggwart's Fund", "3200", "12", "volar@morgulis.si");
        // overit ze totalIncome nie je prazdny
        WebElement interest = driver.findElement(By.cssSelector("div.result > div:nth-child(2) > p"));
        System.out.println("INTEREST: " + interest.getText());
        Assert.assertFalse(interest.getText().isEmpty());
        Assert.assertTrue(interest.getText().contains("kr"));
    }

    @Test
    public void itShouldCalculateRiskIncome() {
        // 1. vybrat fond, zadat sumu, roky, email
        insertValues("Hoggwart's Fund", "3200", "12", "volar@morgulis.si");
        // overit ze totalIncome nie je prazdny
        WebElement risk = driver.findElement(By.cssSelector("div.result > div:nth-child(3) > p"));
        System.out.println("RISK: " + risk.getText());
        Assert.assertFalse(risk.getText().isEmpty());
        Assert.assertFalse(risk.getText().contains("kr"));
    }

    @Test
    public void itShouldCalculateTotalIncomeForEachFund() throws InterruptedException {
        //String[] arrayOfFunds={"Handelsbanken Aktiv 100", "Hoggwart's Fund", "Death Star real estate"};
        //String[] arrayOfFunds = driver.findElement(By.id("fundSelect")).
        Select option = new Select(driver.findElement(By.id("fundSelect")));
        //option.selectByVisibleText();
        List<WebElement> arrayOfFunds = option.getOptions();

        for (WebElement s : arrayOfFunds) {
            //System.out.println(s.getText());
            insertValues(s.getText(), "3200", "12", "volar@morgulis.si");
            if (s.getText().contains("Select your fund")) {

            } else {
                Thread.sleep(1000);
                WebElement interest = driver.findElement(By.cssSelector("div.result > div:nth-child(2) > p"));
                System.out.println("-----------------------------------");
                System.out.println("Fund " + s.getText());
                System.out.println("Total income: " + getTotalIncome());
                System.out.println("Interest income: " + getInterestIncome());
                System.out.println("Total risk: " + getRisk());
                Assert.assertTrue(interest.getText().contains("kr"));
            }
        }
        System.out.println("-----------------------------------");
    }
}
