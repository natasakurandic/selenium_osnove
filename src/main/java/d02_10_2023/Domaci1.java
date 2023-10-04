package d02_10_2023;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Domaci1 {
        public static void main(String[] args) throws InterruptedException{
            WebDriverManager.chromedriver().setup();


            WebDriver driver = new ChromeDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            List<String> imagePaths = new ArrayList<>(Arrays.asList("images_for_Box/back.jpg","images_for_Box/front.jpg", "images_for_Box/left.jpg", "images_for_Box/right.jpg"));

            driver.navigate().to("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
            WebElement editIcon = driver.findElement(By.id("active-face"));
            editIcon.click();
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("image-option-remove")));
            deleteButton.click();

            for (int i = 0; i < imagePaths.size(); i++) {
                editIcon.click();
                wait
                        .withMessage("Upload option didn't appear!")
                        .until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")))
                        .sendKeys(new File(imagePaths.get(i)).getAbsolutePath());

                wait
                        .withMessage("Image didn't upload!")
                        .until(ExpectedConditions.numberOfElementsToBe(By.className("sc-brKeYL"), i + 1));
                driver.findElement(By.id("image-option-0")).click();
                wait
                        .withMessage("Can't upload, there is no submit button!")
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit']"))).click();

                Thread.sleep(2000);
            }
            wait
                    .withMessage("Next button is not clickable")
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sc-ejDCVS.bObSCL"))).click();


            driver.findElement(By.tagName("textarea")).click();
            driver.findElement(By.tagName("textarea")).sendKeys("Svetlana");
            Thread.sleep(1000);

            driver.findElement(By.id("next-button")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("next-button")).click();


            Actions actions = new Actions(driver);
            WebElement main = driver.findElement(By.tagName("main"));
            actions.moveToElement(main);
            actions.clickAndHold()
                    .moveByOffset(-400,300)
                    .moveByOffset(400,100);
            actions.release().perform();
            Thread.sleep(1500);

            driver.findElement(By.id("next-button")).click();
            driver.findElement(By.id("next-button")).click();

            Thread.sleep(5000);
            driver.quit();
        }
    }

