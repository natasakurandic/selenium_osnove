package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasicPage {
    public CartPage (WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public boolean checkIfAddedItemsExist(){
        return elementExists(By.className("cart_item"));
    }
    public WebElement getItemTitleElement(){
        return driver.findElement(By.className("inventory_item_name"));
    }
    public WebElement getItemDescriptionElement(){
        return driver.findElement(By.className("inventory_item_desc"));
    }
    public WebElement getItemPriceElement(){
        return driver.findElement(By.className("inventory_item_price"));
    }
    public String getTextFromAddedItemName(){
        return  getItemTitleElement().getText();
    }

    public void waitForItemTitle(){
        wait
                .withMessage("Item title is not visible in the cart")
                .until(ExpectedConditions.visibilityOf(getItemTitleElement()));
    }

    public String getNameFromAddedItem(){
        return  driver.findElement(By.className("inventory_item_name")).getText();
    }
    public void waitForItemDescription(){
        wait
                .withMessage("Item description is not visible in the cart")
                .until(ExpectedConditions.visibilityOf(getItemDescriptionElement()));
    }
    public void waitForItemPrice(){
        wait
                .withMessage("Item price is not visible in the cart")
                .until(ExpectedConditions.visibilityOf(getItemPriceElement()));
    }

}
