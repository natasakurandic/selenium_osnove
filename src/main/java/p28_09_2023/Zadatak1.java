package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Zadatak1 {
//    Zadatak
//    Ucitati stranicu https://demoqa.com/modal-dialogs
//    Kliknuti na dugme Large modal
//    Proverite da li se prikazao dijalog i ispisite u konzoli odgovarajuce poruke

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();


        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showLargeModal")).click();

            WebElement largeModel = null;
            boolean prikazaoSe = true;

            try {
                largeModel = driver.findElement(By.id("showLargeModal"));
            } catch (Exception e) {
                prikazaoSe = false;
                System.out.println("Dijalog se nije prikazao");
            }
            if (prikazaoSe) {
                largeModel.click();
                System.out.println("Dijalog se prikazao");
            }
            }

    }


