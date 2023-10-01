package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.Keys.ENTER;

public class Zadatak2 {

//    Napisati program koji ucitava stranicu https://youtube.com i u search baru unosi tekste Breskvica i ceka da se
//    pojavi vise od 3 rezultata iz padajuceg menija i zatim klikce na prvi.

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to("https://youtube.com");

        WebElement searchBar = driver.findElement(By.name("search_query"));
        searchBar.click();
        Thread.sleep(1000);
        searchBar.sendKeys("Breskvica");

        wait
                .withMessage("Pretraga nije prikazala preporuke")
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[role='presentation']"), 3));

        Thread.sleep(5000);
        driver.quit();
    }
}
