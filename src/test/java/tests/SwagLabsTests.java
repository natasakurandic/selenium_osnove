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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

        topNavPage.waitForHamburgerButton();
    }
    @Test (priority = 13)
    public void verifyIfTheCartIconIsPresentedOnCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        topNavPage.clickOnCartButton();

        topNavPage.waitForCartIcon();
    }
    @Test (priority = 14)
    public void verifyIfTheHamburgerButtonIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();
    }
    @Test (priority = 15)
    public void verifyIfTheCartIconIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "cart.html",
                "Should be redirected to cart page after click on cart button");
    }
    @Test (priority = 16)
    public void verifyNumberOfAddedItemsToCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();

        int numberOfItemsBefore = topNavPage.getNumberOfItemsFromCartBadge();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        int numberOfItemsAfter = topNavPage.getNumberOfItemsFromCartBadge();
        Assert.assertEquals(numberOfItemsAfter, numberOfItemsBefore + 1, "Number of items in the cart did not increase");
    }
    @Test (priority = 17)
    public void verifySubHeaderTitleOnCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();

        Assert.assertEquals(topNavPage.getTextFromSubheaderTitle(), "Your Cart",
                "Sub-header title should be Your Cart");
    }
    @Test (priority = 18)
    public void verifyNumberOfMenuOptionInLeftNavigationMenu () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();

        Assert.assertEquals(leftNavPage.getNumberOfMenuOptions(), 4,
                "There should be four menu options in left navigation");
    }
    @Test (priority = 19)
    public void verifyTheSpellingOfAllOptionsInMenu () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();

        List<String> correctSpelledMenu = new ArrayList<>(Arrays.asList("All Items", "About", "Logout", "Reset App State"));

        for (int i = 0; i < leftNavPage.getNumberOfMenuOptions(); i++) {
            Assert.assertEquals(leftNavPage.getTextFromMenuOptions().get(i), correctSpelledMenu.get(i),
                    leftNavPage.getTextFromMenuOptions().get(i) + " is not correctly spelled");
        }
    }

    @Test(priority = 20)
    public void verifyAllItemsMenuOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();

        leftNavPage.clickAllItems();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                baseUrl + "inventory.html",
                "Should be redirected to products page after login.");
    }
    @Test(priority = 21)
    public void verifyAboutMenuOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();

        leftNavPage.clickAbout();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://saucelabs.com/",
                "Should be redirected to sauce labs website.");
    }
    @Test(priority = 22)
    public void verifyLogoutMenuOptionIsWorking() {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();

        leftNavPage.logoutLinkClick();
        Assert.assertTrue(
                loginPage.doesUsernameInputExist(),
                "Should be redirected to login page after logout.");
    }
    @Test (priority = 23)
    public void verifyResetAppStateMenuOptionIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        boolean badgeExists = topNavPage.checkIfCartBadgeExists();

        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitForMenuToBeVisible();
        leftNavPage.clickResetAppState();

        boolean badgeExistsAfterReset = topNavPage.checkIfCartBadgeExists();
        Assert.assertEquals(badgeExistsAfterReset, !badgeExists, "Reset option is not resetting the app");
    }
    @Test (priority = 24)
    public void verifyXButtonIsPresentOnLeftNav () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitXButton();
    }
    @Test (priority = 25)
    public void verifyXButtonIsWorkingOnLeftNav () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        topNavPage.clickOnCartButton();
        topNavPage.clickOnHamburgerButton();
        leftNavPage.waitXButton();
        leftNavPage.clickXButton();
        leftNavPage.waitLeftNavMenuDissapear();
    }
    @Test (priority = 26)
    public void verifyIfAddedItemIsPresentOnTheCartPage () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        String nameOfAddedItem = "Sauce Labs Backpack";
        Assert.assertEquals(cartPage.getTextFromAddedItemName(), nameOfAddedItem,
                "Item added is not the same as item in the cart");
    }
    @Test (priority = 27)
    public void  verifyIfAddedItemTitleIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemTitle();
    }
    @Test (priority = 28)
    public void  verifyIfAddedItemDescriptionIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemDescription();
    }
    @Test (priority = 29)
    public void verifyIfAddedItemPriceIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemPrice();
    }
    @Test (priority = 30)
    public void verifyIfAddedItemQuantityIsPresented () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemQuantity();
    }
    @Test (priority = 31)
    public void verifyIfTitleOfAddedItemIsClickable () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemTitleToBeClickable();
    }
    @Test (priority = 32)
    public void verifyIfTitleOfAddedItemIsWorking () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        Assert.assertTrue(cartPage.checkIfAddedItemsExist(), "There are no items in the cart");
        cartPage.waitForItemTitleToBeClickable();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"),"User is not on the item page");
    }
    @Test (priority = 33)
    public void verifyIfRemoveButtonIsVisibleInTheCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForRemoveButtonToBeVisible();
    }
    @Test (priority = 34)
    public void verifyIfRemoveButtonIsWorkingInTheCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.clickOnRemoveButton();

        Assert.assertFalse(cartPage.checkIfAddedItemsExist(), "Added item is not removed");
    }
    @Test (priority = 35)
    public void verifyIfContinueShoppintButtonIsPresentedInTheCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.clearAndTypeUserName(username);
        loginPage.clearAndTypePassword(password);
        loginPage.clickLoginButton();
        inventoryPage.clickAddToCart();
        topNavPage.clickOnCartButton();
        cartPage.waitForContinueShoppingButtonToBeVisible();
    }
}
