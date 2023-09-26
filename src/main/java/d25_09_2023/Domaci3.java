package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Domaci3 {
//    Zadatak
//    Napisati program koji vrsi dodavanje 5 redova
//    Maksimizirati prozor
//    Ucitati stranicu https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php
//    Dodati 5 redova sa istim podacima.Jedan red u jednoj iteraciji
//    Klik na dugme Add New
//    Unesite name,departmant i phone (uvek iste vrednost)
//    Trazenje po name atributu
//    Kliknite na zeleno Add dugme.
//            PAZNJA: Pogledajte strukturu stranice i videcete da se u svakom redu poslednje kolone javljaju dugmici edit,
//    add, delete ali zbog prirode reda neki dugmici se vide a neki ne.
//    Morate da dohvatite uvek Add dugme iz poslednjeg reda tabele. Mozete koristeci index iz petlje, a mozete koristeci
//    i last() fukncionalnost za xpath. Koristan link last mehnizma
//    Cekanje od 0.5s
//    Na kraju programa ugasite pretrazivac.

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.tutorialrepublic.com/snippets/bootstrap/table-with-add-and-delete-row-feature.php");
            Thread.sleep(1000);


            for (int i = 0; i < 5; i++) {
                driver.findElement(By.cssSelector("button.add-new")).click();
                WebElement nameInput = driver.findElement(By.cssSelector("input[name='name']"));
                    nameInput.sendKeys("Perica");
                WebElement departmentInput = driver.findElement(By.cssSelector("input[name='department']"));
                    departmentInput.sendKeys("Frontend Developer");
                WebElement phoneInput = driver.findElement(By.cssSelector("input[name='phone']"));
                    phoneInput.sendKeys("(381) 324-8765");
                WebElement saveButton = driver.findElement(By.cssSelector("body > div > div > div > table > tbody > tr:last-child > td > a.add"));
                        saveButton.click();
                        Thread.sleep(500);
            }
            driver.quit();

    }

}
