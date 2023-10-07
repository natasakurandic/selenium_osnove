package d03_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import d02_10_2023.Helper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BootstrapTableTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void beforeMethod () {
        driver.get("https://s.bootsnipp.com/iframe/K5yrx");
    }
    @Test
    public void editRow () {
        String firstName = "Natasa";
        String lastName = "Kurandic";
        String middleName = "Nale";

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "Tittle of the page is not Table with Edit and Update Data - Bootsnipp.com");

        driver.findElement(By.xpath("//*[@id='d1']/td[5]/button")).click();
        wait
                .withMessage("Edit dialogue is not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='edit']/div[2]")));

        WebElement firstNameInput = driver.findElement(By.id("fn"));
        WebElement lastNameInput = driver.findElement(By.id("ln"));
        WebElement middleNameInput = driver.findElement(By.id("mn"));

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        middleNameInput.clear();
        middleNameInput.sendKeys(middleName);

        driver.findElement(By.xpath("//*[@id='up']")).click();

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(), firstName, "First name should be " +firstName);
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(), lastName, "Last name should be " +lastName);
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(), middleName, "Middle name should be " +middleName);
    }
    @Test
    public void deleteRow () {
        int cellsInARow = driver.findElements(By.cssSelector(".table>tbody>tr:first-child>td")).size();
        int cellsInATable = driver.findElements(By.cssSelector(".table>tbody>tr>td")).size();

        driver.findElement(By.xpath("//*[@id='d1']/td[6]/button")).click();

        wait
                .withMessage("Edit dialogue is not visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='delete']/div[2]")));

        driver.findElement(By.xpath("//*[@id='del']")).click();
        wait
                .withMessage("Delete dialogue is still visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='delete']/div[2]")));

        Assert.assertEquals(driver.findElements(By.cssSelector(".table>tbody>tr>td")).size(),
            cellsInATable - cellsInARow, "Row is still visible after deletion");
    }
    @Test
    public void takeAScreenshot () throws IOException {
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f,new File("D:\\Projekti Nis- Novi Sad\\selenium_osnove\\screenshots\\screenshot2.jpg"));
    }
    @AfterClass
    public void afterClass () {
        driver.quit();
    }
}
