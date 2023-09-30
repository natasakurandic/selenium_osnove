package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Domaci1 {
//    1.Zadatak
//    Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
//    Klik na Type drawdown
//    Klik na Public iz drowdowna
//    Ceka da se Clear dugme u desnom uglu prikaze koristeci explicit wait
//    Kilk na Clear filter u desnom uglu

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort= ");

        WebElement type = driver.findElement(By.cssSelector("#type-options > summary"));
            type.click();

        WebElement publicDrowdawn = driver.findElement(By.cssSelector("label:nth-child(2)"));
            publicDrowdawn.click();

        WebElement clearButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(" div.TableObject-item.text-right.v-align-top")));

        clearButton.click();

        driver.quit();
    }

}
