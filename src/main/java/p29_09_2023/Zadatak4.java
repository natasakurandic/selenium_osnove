package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Zadatak4 {
//    Napisati program koji
//    ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//    Klik Primary dugme
//    Ceka da se pojavi toasts u gornjem desnom uglu
//    Ispisuje da se element pojavio
//    Ceka da se izgubi toasts u gornjem desnom uglu
//    Ispisuje da se elment izgubio
//    Klik na primary dugme
//    Ceka da se pojavi toasts u gornjem desnom uglu
//    Ispisuje da se element pojavio
//    Klik na x dugme iz toasts-a
//    Ceka da se element izgubi
//    Ispisuje da se element izgubio

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        WebElement primaryButton = driver.findElement(By.xpath("//button[text()='Primary']"));
            primaryButton.click();

        WebElement toasts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'toast')]")));
        System.out.println("Element se pojavio");
        wait.until(ExpectedConditions.visibilityOf(toasts));
        System.out.println("Element se izgubio");
        primaryButton.click();
        toasts = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'toast')]")));
        System.out.println("Element se pojavio");
        WebElement closeButton = driver.findElement(By.cssSelector("div.toast-header.toast-primary > button"));
        closeButton.click();
        wait.until(ExpectedConditions.visibilityOf(toasts));
        System.out.println("Element se izgubio");


        driver.quit();
    }

}
