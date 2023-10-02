package p02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak1 {
//    Napisati program koji:
//    Krairajte folder za fajlove u okviru projekta pod nazivom test_data
//    U folder skinite i postavite proizvoljnu sliku
//    Ucitava stranu https://tus.io/demo.html
//    Skrola do dela za upload fajla
//    Aploadujte sliku
//    Cekajte da se pojava dugme za download fajla

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://tus.io/demo.html");
        driver.manage().window().maximize();

        File uploadFile = new File("test_data/istockphoto-877369086-612x612.jpg");

        driver
                .findElement(By.id("P0-0"))
                        .sendKeys(uploadFile.getAbsolutePath());

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.cssSelector("._buttons_gq6c0_28 > a"), 1));

        driver.quit();
    }

}
