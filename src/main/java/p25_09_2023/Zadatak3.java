package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class Zadatak3 {

//    Napisati program koji:
//    Ucitava stranicu https://demoqa.com/text-box
//    Unosi informacije za 3 osobe koristeci petlju
//    Full Name
//    Email
//    Current Address
//    Permanent Address
//    Klik na submit
//    Ceka 2 sekunde
//    Osvezava stranicu
//    Zatvara pretrazivac

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/text-box");

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.id("userName")).sendKeys("Natasa" + i);
            driver.findElement(By.id("userEmail")).sendKeys("Natasa" + i + "@gmail.com");
            driver.findElement(By.id("currentAddress")).sendKeys("Natasa adr" + i);
            driver.findElement(By.id("permanentAddress")).sendKeys("Natasa per adr" + i);
            driver.findElement(By.id("submit")).click();

            Thread.sleep(2000);
            driver.navigate().refresh();



        }

            driver.quit();
    }

}
