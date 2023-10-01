package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

import java.time.Duration;

public class Zadatak6 {
//    Napisati program koji:
//    Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//    Vrsi klik na Primary dugme, Secondary, Sucess, Danger
//    Ceka da broj toasts-a bude 4
//    Ispisuje poruku, toasts su prikazani
//    Ceka da broj toasts-a bude 0
//    Ispisuje poruku, toasts su se izgubili

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");
        driver.manage().window().maximize();

        WebElement primaryButton = driver.findElement(By.id("basic-primary-trigger"));
        primaryButton.click();
        WebElement secondaryButton = driver.findElement(By.id("basic-secondary-trigger"));
        secondaryButton.click();
        WebElement successButton = driver.findElement(By.id("basic-success-trigger"));
        successButton.click();
        WebElement dangerButton = driver.findElement(By.id("basic-danger-trigger"));
        dangerButton.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class,'toast-fixed show')]"), 4));
        System.out.println("Toasts su prikazani");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[contains(@class,'toast-fixed show')]"), 0));
        System.out.println("Toasts su se izgubili");

        driver.quit();
    }
}
