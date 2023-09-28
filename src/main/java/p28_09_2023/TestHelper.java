package p28_09_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class TestHelper {
    private WebDriver driver;

    public TestHelper(WebDriver driver) {
        this.driver = driver;
    }
    public boolean elementExists (By by) {
        try {
            driver.findElement(by);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        public boolean elementExistsByList (By by) {
            return !driver.findElements(by).isEmpty();
        }
        public void setDefaultImplicitWait () {
        this.driver
                .manage()
            .timeouts()
            .implicitlyWait(Duration.ofSeconds(10));
        }
    public void setImplicitWait(int seconds) {
        this.driver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(seconds));
    }
    }

