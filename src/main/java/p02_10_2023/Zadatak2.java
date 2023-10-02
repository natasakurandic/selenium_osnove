package p02_10_2023;

//Napisati program koji:
//        Ucitava stranu https://blueimp.github.io/jQuery-File-Upload/
//        Uploadujte sliku
//        Ceka se da se pojavi slika u listi uploadovanih fajlova
//        Koristite uslov da broj elemenata bude 1.
//        Klik na Start dugme u okviru item-a koji se uploadovao
//        Ceka se da se pojavi delete dugme pored itema
//        Klik na delete dugme pored itema
//        Ceka se da se element obrise
//        Koristite da broj elemenata bude 0

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Zadatak2 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.navigate().to("https://blueimp.github.io/jQuery-File-Upload/");

        File uploadFile = new File("test_data/istockphoto-877369086-612x612.jpg");

        driver
                .findElement(By.cssSelector("input[type=file]"))
                .sendKeys(uploadFile.getAbsolutePath());

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".files > tr"), 1));
        driver.findElement(By.cssSelector(" button.btn.btn-primary.start")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".files button.delete")));
        driver.findElement(By.cssSelector(".files button.delete")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".files > tr"), 0));

        driver.quit();
    }
}
