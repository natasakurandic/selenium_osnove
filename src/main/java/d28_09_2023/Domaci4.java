package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Domaci4 {
//    4.Zadatak
//    Ucitati stranicu http://seleniumdemo.com/?post_type=product
//    Klik na search dugme u gornjem desnom uglu
//    Cekati da forma za pretragu bude vidljiva
//    Uneti sledeci tekst za pretragu BDD Cucumber i ENTER
//    Dohvatiti prvi rezultat pretrage i proveriti da li u nazivu sadrzi tekst koji je unet za pretragu.
//    odgovarajuce poruke u terminalu

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.navigate().to(" http://seleniumdemo.com/?post_type=product");

        WebElement searchbutton = driver.findElement(By.xpath("//*[@id=\"tc-page-wrap\"]/header/div[1]/div/div/div[2]/ul/li[1]/a"));
        searchbutton.click();
        WebElement searchForm = driver.findElement(By.xpath("//*[@id=\"s-651536d633c09\"]"));
        wait.until(ExpectedConditions.visibilityOf(searchForm));
        searchForm.sendKeys("BDD Cucumber");
        searchForm.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article:first-child .czr-title")));
        if(driver.findElement(By.cssSelector("article:first-child .czr-title")).getText().contains("BDD Cucumber")) {
            System.out.println("The name contains the text entered for the search");
        } else {
            System.out.println("The name not contains the text entered for the search");
        }
        Thread.sleep(1500);
        driver.quit();
    }
}
