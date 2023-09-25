package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak4 {
//    Napisati program koji:
//    Maksimizuje stranicu
//    Ucitava stranicu https://demoqa.com/webtables
//    Vrsi klik na edit dugme prvog reda
//    Unosi informacije za sva polja u edit formi
//    Klik na submit
//    Ceka par sekundi
//    Zatvara pretrazivac


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/webtables");


            driver.findElement(By.xpath("//*[@id=\"edit-record-1\"]")).click();

            driver.findElement(By.xpath("//*[@id=\"firstName\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"firstName\"]")).sendKeys("Pera");

            driver.findElement(By.xpath("//*[@id=\"lastName\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"lastName\"]")).sendKeys("Peric");

            driver.findElement(By.xpath("//*[@id=\"userEmail\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"userEmail\"]")).sendKeys("pera1@gmail.com");

            driver.findElement(By.xpath("//*[@id=\"age\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"age\"]")).sendKeys("30");

            driver.findElement(By.xpath("//*[@id=\"salary\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"salary\"]")).sendKeys("50000");

            driver.findElement(By.xpath("//*[@id=\"department\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"department\"]")).sendKeys("New Department");


            driver.findElement(By.cssSelector("#submit")).click();
            Thread.sleep(3000);

            driver.quit();
    }
}
