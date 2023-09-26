package p26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak5 {

//    5. Zadatak
//    Napisati program koji ucitava stranicu https://demoqa.com/automation-practice-form, cekira Remember me checkbox.
//    Na kraju programa proverite da li je element cekiran. Izguglajte kako mozemo da proverimo da li je element cekiran.


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/automation-practice-form");


        WebElement label = driver.findElement(By.cssSelector("#hobbiesWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label"));
//        label.click();
        Thread.sleep(1000);

        WebElement checkbox = driver.findElement(By.cssSelector("#hobbies-checkbox-1"));
        if (checkbox.isSelected()) {
            System.out.println("Checkbox je cekiran.");
        } else {
            System.out.println("Checkbox nije cekiran");
        }

        driver.quit();

    }
}
