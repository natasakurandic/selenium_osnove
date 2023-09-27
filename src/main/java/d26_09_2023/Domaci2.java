package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Domaci2 {
//    Napisati program koji:
//    Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//    Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje
//    odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//    POMOC: Brisite elemente odozdo.
//            (ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://s.bootsnipp.com/iframe/Dq2X");
        Thread.sleep(1000);

        List<WebElement> buttons = driver.findElements(By.cssSelector(".alert>button"));
        for (int i = buttons.size() -1; i >= 0; i--) {
            int prethodnoStanjeListe = buttons.size();
            System.out.println(prethodnoStanjeListe);
            buttons.get(i).click();
            Thread.sleep(1000);
            buttons = driver.findElements(By.cssSelector(".alert>button"));
            if (prethodnoStanjeListe - buttons.size() == 1) {
                System.out.println("Element je obrisan");
            } else {
                System.out.println("Element nije obrisan");
            }
            Thread.sleep(2000);
        }
        driver.quit();
    }
}
