package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak6 {
//6. Ucitati stranicu https://google.com
//    Maksimizovati prozor
//    Prostavite dimenzije prozora na 700px i visinu na 700px

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.navigate().to("https://google.com");
        Thread.sleep(1000);

        Dimension newSize = new Dimension(700, 700);
        driver.manage().window().setSize(newSize);

        Thread.sleep(5000);
        driver.quit();
    }

}
