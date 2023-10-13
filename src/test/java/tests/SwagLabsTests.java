package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LeftNavPage;
import pages.InventoryPage;
import pages.TopNavPage;
import retry.SwagLabsRetry;


public class SwagLabsTests extends BasicTest {

    private LoginPage loginPage;
    private LeftNavPage leftNavPage;
    private InventoryPage inventoryPage;
    private TopNavPage topNavPage;

    @Test(priority = 1)
    public void VerifyErrorIsDisplayedWhenUsernameIsMissing () {
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test(priority = 2)
    public void VerifyErrorIsDisplayedWhenPasswordIsMissing () {
        String username = "standard_user";

        loginPage.clearAndTypeUserName(username);
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
    }
    @Test(priority = 3)
    public void VerifyErrorIsDisplayedWhenCredentialsAreWrong () {
        String username = "standard_user";
        String password = "invalidpassword";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }
    @Test(priority = 4)
    public void VerifyErrorIsDisplayedWhenUserIsLocked () {
        String username = "locked_out_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        String errorMessage = loginPage.getLoginErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out");
    }
    @Test(priority = 5)
    public void VerifySuccessfulLogin () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/inventory.html", "Should be redirected to inventory page after login.");
        leftNavPage.menuButtonClick();
        leftNavPage.waitForMenuToBeVisible();
        Assert.assertTrue(leftNavPage.doesLogoutButtonExist(), "Logout link should exists on menu");
        leftNavPage.logoutLinkClick();
        Assert.assertTrue(loginPage.getUserNameField().isDisplayed(), "Should be redirected to login page after logout.");
    }
    @Test(priority = 6)
    public void  AddingProductsToCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "/inventory.html", "Should be redirected to inventory page after login.");

        Assert.assertTrue(inventoryPage.locateSauceLabsItem(), "There is no Sauce Labs Backpack");

        inventoryPage.productSauceLabsBackpackAdd();
        inventoryPage.removeButtonVisibility();
        inventoryPage.cartProducts();
    }
    @Test (priority = 7)
    public void verifyUrlOfCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        Assert.assertEquals(
                driver.getCurrentUrl(), baseUrl + "cart.html",
                "User should be on the cart page!");
    }
    @Test (priority = 8)
    public void verifyPageTitleOfCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        Assert.assertEquals(driver.getTitle(), "Swag  Labs",
                "Title of cart page should be Swag Labs");
    }
    @Test (priority = 9)
    public void verifyHeaderTitleOfCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        topNavPage.titleInHeader();
    }
    @Test (priority = 10)
    public void verifyIfTheHamburgerMenuButtonIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        topNavPage.hamburgerMenuButtonIsPresented();
    }
    @Test (priority = 11)
    public void verifyIfTheCartIconIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        topNavPage.cartIconIsPresented();
    }
    @Test (priority = 12)
    public void verifyIfTheHamburgerButtonIsPresentedOnCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        Assert.assertTrue(topNavPage.hamburgerButtonIsEnabled(), "Hamburger button is not enabled");
    }
    @Test (priority = 13)
    public void verifyIfTheCartIconIsPresentedOnCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        Assert.assertTrue(topNavPage.cartIconIsEnabled(), "Cart icon is not enabled");
    }
}
