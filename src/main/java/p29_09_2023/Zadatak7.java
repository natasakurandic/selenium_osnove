package p29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Zadatak7 {
//    Napisati program koji:
//    Ucitava stranicu https://tus.io/demo.html
//    Hvata sve linkove sa stranice
//    Skrola do svakog h3 elementa
//    Od svakog h3 elementa cita text


    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://tus.io/demo.html");

        List<WebElement> links = driver.findElements(By.xpath("//link"));
        List<WebElement> h3Elements = driver.findElements(By.xpath("//h3"));

        for (int i = 0; i < links.size(); i++) {
            System.out.println("Link: " + links.get(i).getAttribute("href"));
        }

        for (int i = 0; i < h3Elements.size(); i++) {
            Actions actions = new Actions(driver);
            actions.scrollToElement(h3Elements.get(i));
            actions.perform();
            WebElement h3 = h3Elements.get(i);
            System.out.println("Tekst h3 elementa: " + h3.getText());
        }
        driver.quit();
    }
}
