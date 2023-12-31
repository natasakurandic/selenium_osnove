package d22_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Domaci1 {
//    Napisati program koji:
//    ima niz od 5 stringova. Svaki element u nizu je url stranice:
//    https://www.google.com/
//    https://www.facebook.com/
//    https://www.youtube.com/
//    https://www.ebay.com/
//    https://www.katalon.com/
//    kreira testnu infrastukturu
//    zatim koristeci for petlju otvara svaku stranicu iz niza u pretrazivacu i pritom pravi pauzu od 2 sekunde
//    izmedju svaka dva ucitanja stranice
//    Na kraju program ponisava testnu ifrastukturu

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        ArrayList<String> urlStranice = new ArrayList<>();
        urlStranice.add("https://www.google.com/");
        urlStranice.add("https://www.facebook.com/");
        urlStranice.add("https://www.youtube.com/");
        urlStranice.add("https://www.ebay.com/");
        urlStranice.add("https://www.katalon.com/");

        for (int i = 0; i < urlStranice.size(); i++) {
                driver.get(urlStranice.get(i));
            Thread.sleep(2000);
        }

        driver.quit();
    }
}
