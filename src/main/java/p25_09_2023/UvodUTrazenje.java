package p25_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UvodUTrazenje {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://cms.demo.katalon.com/my-account/");


        //      //*[@id='primary-menu']/ul/li[1]/a
        //      #primary-menu > ul > li.page_item.page-item-8 > a

//        WebElement cartLink = driver.findElement(
//                By.xpath(
//                        "//*[@id=\"primary-menu\"]/ul/li[1]/a"));

//        WebElement cartLink = driver.findElement(
//                By.cssSelector("#primary-menu > ul > li.page_item.page-item-8 > a"));

        driver.findElement(By.cssSelector("#username"))
                        .sendKeys("username");





        driver.quit();
    }
}
