package p03_10_2023;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class UvodUTesting {

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
        driver.navigate().to("htttp://google.com");
    }

    @Test
    public void googleTitleTest() {
        Assert.assertTrue(driver.getTitle().contains("Google"), "Obavezna poruka jer se koristi boolean");
        System.out.println("Naslov: " + driver.getTitle());
        driver.navigate().to("http://facebook.com");

        if (driver.getTitle().equals("Google")) {
            System.out.println("Dobro je");
        } else {
            System.out.println("Nije dobro");
        }

        Assert.assertEquals(driver.getTitle(), "Google");
        System.out.println("PRINT POSLE GRESKE");
        Assert.assertEquals(driver.getCurrentUrl(), "https://google.com", "");
        WebElement searchInput =driver
                .findElement(By.name("q"));
        searchInput.sendKeys("it bootcamp");

        searchInput.sendKeys(Keys.ENTER);

        wait
                .withMessage("After .......")
                .until(ExpectedConditions.titleContains("it bootcamp"));


    }

    @Test
    public void googleSearchTest() {
        driver.navigate().to("htttp://google.com");
        driver
                .findElement(By.name("q"))
                        .sendKeys("IT Bootcamp");
    }
    @AfterClass
    public void afterClass () {
        System.out.println("After Class");
        driver.quit();
    }

}