package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Domaci2Vezbanje {
//    Napisati program koji:
//    Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//    Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje
//    odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//            (ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo



    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.navigate().to("https://s.bootsnipp.com/iframe/Dq2X");
        Thread.sleep(1000);

        List<WebElement> buttons1 = driver.findElements(By.cssSelector(".alert>button"));
        while (buttons1.size() > 0) {
            int prethodnoStanjeListe = buttons1.size();
            System.out.println(prethodnoStanjeListe);
            buttons1.get(0).click();
            Thread.sleep(1000);
            buttons1 = driver.findElements(By.cssSelector(".alert>button"));
            if (prethodnoStanjeListe - buttons1.size() == 1) {
                System.out.println("Element je obrisan");
            } else {
                System.out.println("Element nije obrisan");
            }
            Thread.sleep(2000);
        }
        driver.quit();
    }

}
