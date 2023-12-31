package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

    public  LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement getUserNameField () {
        return  driver.findElement(By.id("user-name"));
    }
    public WebElement getPasswordInput() {
        return driver.findElement(By.id("password"));
    }
    public String getLoginErrorMessage () {
        return driver.findElement(By.cssSelector("error-message-container h3")).getText();
    }

        public boolean doesUsernameInputExist() {
            return elementExists(By.id("user-name"));
        }
    public void clearAndTypeUserName (String firstName) {
        getUserNameField()
                .clear();
        getUserNameField().sendKeys(firstName);
    }
    public void clearAndTypePassword (String password) {
        getUserNameField()
                .clear();
        getUserNameField().sendKeys(password);
    }
    public void clickLoginButton () {
        driver.findElement(By.id("login-button"))
                .click();
    }

}
