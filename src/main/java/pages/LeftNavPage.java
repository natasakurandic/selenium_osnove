package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftNavPage extends BasicPage {
    public LeftNavPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public void menuButtonClick () {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }
        public void waitForMenuToBeVisible () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));
        }
        public boolean doesLogoutButtonExist () {
        return elementExists(By.linkText("Logout"));
        }
        public void logoutLinkClick () {
        driver.findElement(By.id("logout_sidebar_link")).click();
        }

}
