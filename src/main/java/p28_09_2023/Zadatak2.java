package p28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Zadatak2 {
//    Kreirati klasu TestHelper koja ima:
//    privatan atribut driver
//    kontukstor sa parametrom
//    metodu elementExists koja vraca true ili false ako element postoji. Metoda kao parametar prima nacin trazenja By
//    objekat. Metoda radi proveru preko TryCatch-a
//    metodu elementExistsByList koja takodje vraca true ili false. Metoda kao parametar prima By objekat za trazenje.
//    metodu setDefaultImplicitWait koja postavlja implicino cekanje na 10s.
//    metodu setImplicitWait koja postavlja implicitno cekanje iz prosledjene vrednosti.
//
//    U glavnom programu resiti prvi zadatak koriseci objekat klase TestHelper za proveru elemenata i za postavljanje
//    implicitnog cekanja.

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showLargeModal")).click();
        TestHelper helper = new TestHelper(driver);
         helper.setDefaultImplicitWait();

        boolean x = helper.elementExists(By.id("example-modal-sizes-title-lg"));
    }

}
