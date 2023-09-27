package d26_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Domaci1 {
//    Zadatak
//    Napisati program koji:
//    Ucitava stranicu https://demoqa.com/automation-practice-form
//    Popunjava formu sta stranice. Korisnik unosi podatke sa tastature za popunu forme.
//            (za vezbanje) Probajte da unese i datum. Sa datumom se radi isto kao i sa obicnim inputom sa sendKeys.
//    Klik na submit

    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/automation-practice-form");
        Thread.sleep(1000);

        System.out.println("Unesite vase ime");
        String firstName = s.next();
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        System.out.println("Unesite vase prezime");
        String lastName = s.next();
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        System.out.println("Unesite vas email");
        String email = s.next();
        driver.findElement(By.id("userEmail")).sendKeys(email);
        System.out.println("Izaberite pol: ");
        String pol = s.next();
        driver.findElement(By.xpath("//label[text()='"+pol.substring(0, 1).toUpperCase() + pol.substring(1).toLowerCase()+"']")).click();
        System.out.println("Unesite broj telefona: ");
        String phoneNumber = s.next();
        driver.findElement(By.id("userNumber")).sendKeys(phoneNumber);
        WebElement dateInput = driver.findElement(By.id("dateOfBirthInput"));
        String inputValue = dateInput.getAttribute("value");
        System.out.println("Unesite subject");
        String subject = s.next();
        driver.findElement(By.id("subjectsInput")).sendKeys(subject);
        System.out.println("Izaberite hobi (Sports, Reading, Music)");
        String hobby = s.next();
        driver.findElement(By.xpath("//label[text()='"+hobby.substring(0,1).toUpperCase()+hobby.substring(1).toLowerCase()+"']")).click();
        System.out.println("Unesite vasu adresu");
        String address = s.next();
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        Thread.sleep(1000);



    driver.quit();
    }

}
