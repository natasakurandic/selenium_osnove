package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class InventoryPage extends BasicPage {
    public InventoryPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean locateSauceLabsItem() {
        return elementExists(By.xpath("//div[text()='Sauce Labs Backpack']"));
    }
    public void productSauceLabsBackpackAdd () {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack"))
                .click();
    }
    public void clickAddToCart () {
        driver.findElement(By.id("add-to-cart-sauce"))
                .click();
    }
    public void removeButtonVisibility () {
        wait
                .withMessage("Remove button is not visible")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));
    }
    public void cartProducts () {
        int productNumberInCartOnStart = 0;
        Assert.assertEquals(Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText()),
                productNumberInCartOnStart + 1,
                "The number of items in the cart did not change!");
    }
}
