package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Domaci2 {

//2.Zadatak
//    Ucitati stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//    Klik na svako dugme od PRIMARY do DARK
//    Sacekati da se toasts u desnom gornjem uglu pojavi
//    Pauza izmedju klikova 1s
//    Postavite implicitno cekanje za ucitavanje stranice i trazenje elemenata na 10s

    public static void main(String[] args) throws  InterruptedException{

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        List<WebElement> buttons = driver.findElements(By.xpath(" //button[contains(@id,'basic')]"));
        List<WebElement> toasts = driver.findElements(By.xpath(" //div[contains(@id, 'basic')]"));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
            wait.until(ExpectedConditions.visibilityOf(toasts.get(i)));
            Thread.sleep(1000);
        }
        driver.quit();
    }

}
