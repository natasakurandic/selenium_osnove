package d25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Domaci2 {
//    Zadatak
//    Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//    Visit Paris
//    Visit Prague
//    Visit London
//    Visit New York
//    Visit Belgrade
//    Maksimizirati prozor
//    Ucitati stranicu https://example.cypress.io/todo
//    Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//    Nakon svakog unosa todo-a, unosi se enter. Koristan link
//    Nakon svih unosa proci petljom kroz svaki todo koji je na stranici i za svaki cekirati da je completed.
//    Cekanje od 5s
//    Zatvorite pretrazivac

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        ArrayList<String> nizGradova = new ArrayList<>();
            nizGradova.add("Visit Paris");
            nizGradova.add("Visit Prague");
            nizGradova.add("Visit London");
            nizGradova.add("Visit New York");
            nizGradova.add("Visit Belgrade");

            driver.navigate().to("https://example.cypress.io/todo");
            Thread.sleep(2000);

            for (int i = 0; i < nizGradova.size(); i++) {
                driver.findElement(By.className("new-todo"))
                        .sendKeys(nizGradova.get(i));
                WebElement textbox = driver.findElement(By.className("new-todo"));
                textbox.sendKeys(Keys.ENTER);
            }
        List<WebElement> todoItems = driver.findElements(By.cssSelector(".todo-list input[type='checkbox']"));
            for (int i = 0; i < todoItems.size(); i++) {
                    todoItems.get(i).click();
            }

            Thread.sleep(5000);

            driver.quit();

    }

}
