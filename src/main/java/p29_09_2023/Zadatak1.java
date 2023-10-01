package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak1 {
//    1.Zadatak
//    Napisati program koji ucitava stranicu https://s.bootsnipp.com/iframe/klDWV i ceka da se ucita progress bar na 100%
//  a zatim ispisuje tekst u konzoli “Stranica ucitana”

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://s.bootsnipp.com/iframe/klDWV");

        WebElement progressBar = driver.findElement(By.cssSelector(".loadbar"));

        wait
                .withMessage("Stranica je ucitana")
            .until(ExpectedConditions.attributeToBe(progressBar, "style", "width: 100%;"));

        System.out.println("Stranica ucitana");

        driver.quit();

    }
}
