package p03_10_2023;

//Kreirati klasu KatalonLoginTests za testove
//        Base url: https://cms.demo.katalon.com
//        Test #1: Visit login page from Nav bar
//        Koraci:
//        Ucitati home stranicu
//        Kliknuti na My account link
//        Verifikovati da je naslov stranice My account – Katalon Shop
//        Verifikovati da se u url-u stranice javlja /my-account
//        Za sve validacije ispisati odgovarajuce poruke u slucaju greske


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Zadatak1 {
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
    public void beforeMEthod () {
        driver.navigate().to("https://cms.demo.katalon.com ");
    }
    @Test
    public void visitLoginPageFromNavBar () {

        driver.findElement(By.cssSelector("#primary-menu > ul > li.page_item.page-item-10 > a")).click();
        wait
                .withMessage("Should be redirected to my account page.")
                .until(ExpectedConditions.titleContains("My account – Katalon Shop"));

        wait
                .withMessage("Should be redirected to my account page.")
                .until(ExpectedConditions.urlContains("/my-account"));
    }
    @Test
    public void checkInputTypes () {
        driver.findElement(By.cssSelector("#primary-menu > ul > li.page_item.page-item-10 > a")).click();
        WebElement inputEmail = driver.findElement(By.id("username"));
        String typeAttributeValue = inputEmail.getAttribute("type");
        Assert.assertEquals(typeAttributeValue, "text", "Type attribute is not text");

        WebElement inputPassword = driver.findElement(By.id("password"));
        String typeAttributeValue1 = inputPassword.getAttribute("type");
        Assert.assertEquals(typeAttributeValue1, "password", "Type attribute is not password");

        WebElement rememberMeCheckbox = driver.findElement(By.id("rememberme"));
        String typeAttributeValue2 = rememberMeCheckbox.getAttribute("type");
        Assert.assertEquals(typeAttributeValue2, "checkbox", "Type attribute is not checkbox");
        Assert.assertTrue(!rememberMeCheckbox.isSelected(), "Checkbox is selected");
    }
    @Test
    public void displayErrorWhenCredentialsAreWrong () {
        String email = "invalidemail@gmail.com";
        String password = "invalid123";

        driver.findElement(By.cssSelector("#primary-menu > ul > li.page_item.page-item-10 > a")).click();
        WebElement inputEmail = driver.findElement(By.id("username"));
        inputEmail.sendKeys(email);
        WebElement inputPassword = driver.findElement(By.id("password"));
        inputPassword.sendKeys(password);
        driver.findElement(By.cssSelector("#post-10 > div > div > form > p:nth-child(3) > button")).click();
        WebElement errorElement = driver.findElement(By.cssSelector("#post-10 > div > div > div > ul > li > strong"));
        Assert.assertTrue(errorElement.isDisplayed(), "Element for error is not displayed");
        String errorText = errorElement.getText();
        Assert.assertTrue(errorText.contains("ERROR: Invalid email address"), "Your email is not valid");
        Assert.assertTrue(driver.getCurrentUrl().contains("/my-account"), "After failed login should stay on same page");

    }
    @Test
    public void successfulLoginWithValidCredentials () {
        String email = "customer";
        String password = "crz7mrb.KNG3yxv1fbn";

        driver.findElement(By.cssSelector("#primary-menu > ul > li.page_item.page-item-10 > a")).click();
        wait
                .withMessage("Should be redirected to my account page.")
                .until(ExpectedConditions.titleContains("My account – Katalon Shop"));

        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();

        wait
                .withMessage("...")
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.linkText("Logout")));
    }
    @AfterClass
    public void AfterClass () {
        driver.quit();
    }

}
