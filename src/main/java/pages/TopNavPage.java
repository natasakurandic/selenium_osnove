package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopNavPage extends BasicPage{

    public TopNavPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public WebElement hamburgerButton() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }
    public WebElement cartButton() {
        return driver.findElement(By.id("shopping_cart_container"));
    }
    public void clickOnCartButton () {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
    public void clickOnHamburgerButton () {
        driver.findElement(By.className("react-burger-menu-btn")).click();
    }

    public void titleInHeader () {
        String expectedHeaderTitle = "Swag Labs";
        WebElement headerTitleElement = driver.findElement(By.className("header_label"));
        String actualHeaderTitle = headerTitleElement.getText();
        assert actualHeaderTitle.equals(expectedHeaderTitle) : "Header title does not match the expected title.";
    }
    public void hamburgerMenuButtonIsPresented () {
        WebElement hamburgerMenuButton = driver.findElement(By.id("react-burger-menu-btn"));
        assert hamburgerMenuButton.isDisplayed() : "Hamburger menu button is not presented on the page.";
    }
    public void cartIconIsPresented () {
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        assert cartIcon.isDisplayed() : "Cart icon is not presented on the page.";
        cartIcon.click();
    }
    public boolean hamburgerButtonIsEnabled (){
        return  hamburgerButton().isEnabled();
    }
    public boolean cartIconIsEnabled () {
        return  cartButton().isEnabled();
    }
}
