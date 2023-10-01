package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Domaci1 {
//    Napisati program koji testira infinity scroll.
//    Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//    Selektujte delay od 2000ms, koristeci Select klasu.
//            Skrol do Show more dugmeta koje se nalazi na dnu stranice
//    Sacekajte da dugme bude klikljivo
//    Klik na Show more dugme
//    Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//    Sacekajte da dugme vise ne bude klikljivo


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");

        WebElement delay = driver.findElement(By.cssSelector("#delay-select"));
        Select select = new Select(delay);
        select.selectByValue("2000");

        WebElement showMoreButton = driver.findElement(By.cssSelector("#infinite-scroll-button"));
        new Actions(driver)
                .moveToElement(showMoreButton)
                .perform();
        wait.until(ExpectedConditions.elementToBeClickable(showMoreButton));
        showMoreButton.click();

        int initialElementCount = driver.findElements(By.className("card")).size();
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("card"), initialElementCount));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#infinite-scroll-button")));

        driver.quit();
    }
}
