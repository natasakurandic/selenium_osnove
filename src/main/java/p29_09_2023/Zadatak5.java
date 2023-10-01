package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Zadatak5 {
//    Napisati program koji:
//    Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/
//    Vrsi klik na Basic example link iz desne navigacije
//    Ceka da url sadrzi #section-basic-example

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/");

        driver.findElement(By.cssSelector("#scrollspy > ul > li:nth-child(2) > a")).click();
        wait.until(ExpectedConditions.urlContains("#section-basic-example"));
        System.out.println("URL sadrzi #section-basic-example");

        driver.quit();
    }

}
