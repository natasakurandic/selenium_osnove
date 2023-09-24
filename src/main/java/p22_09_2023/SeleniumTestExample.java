package p22_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestExample {
    public static void main(String[] args) throws InterruptedException {
        //Postavljanje ChromeDriver koristeci WebDriveManager
        WebDriverManager.chromedriver().setup();
        // Kreiranje instance chromeDriver-a
        WebDriver driver = new ChromeDriver();

        driver.get("https://google.com");
        Thread.sleep(500);
        driver.get("https://facebook.com");
        Thread.sleep(500);
        driver.get("https://youtube.com");
        Thread.sleep(500);
        // Mesto za test kod ...



        // Zatvaranje pretrazivaca nakon sto se zavrsi testiranje
        driver.quit();
    }
}

