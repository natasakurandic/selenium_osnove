package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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
    public List<WebElement> getMenuOptions(){
        return driver.findElements(By.cssSelector(".bm-item-list>a"));
    }
    public int getNumberOfMenuOptions(){
        return getMenuOptions().size();
    }
    public List<String>getTextFromMenuOptions(){
        List<String>menuOptions = new ArrayList<>();

        for (int i = 0; i < getNumberOfMenuOptions(); i++) {
            menuOptions.add(getMenuOptions().get(i).getText());
        }
        return menuOptions;
    }
    public WebElement getAllItems(){
        return driver.findElement(By.linkText("All Items"));
    }
    public void clickAllItems(){
        getAllItems().click();
    }
    public WebElement getAbout(){
        return driver.findElement(By.linkText("About"));
    }
    public void clickAbout(){
        getAbout().click();
    }

}
