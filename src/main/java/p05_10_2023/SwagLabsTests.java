package p05_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static d02_10_2023.Helper.getHTTPResponseStatusCode;

public class SwagLabsTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseURl = "https://www.saucedemo.com/";
    private JavascriptExecutor js;
    @BeforeClass
    public void SetUp () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @BeforeMethod
    public void beforeMethod () {
        driver.manage().deleteAllCookies();
        driver.get(baseURl);
    }
    @Test (priority = 1, retryAnalyzer = SwagLabsRetry.class)
    public void VerifyErrorIsDisplayedWhenUsernameIsMissing () {
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("The message should be: Epic sadface: Username is required")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(
                        "h3[data-test='error']"),
                        "Epic sadface: Username is required"));
    }
    @Test
    public void VerifyErrorIsDisplayedPasswordIsMissing () {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("The message should be: Epic sadface: Password is required")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(
                                "h3[data-test='error']"),
                        "Epic sadface: Password is required"));
    }
    @Test
    public void VerifyErroeIsDisplayedWhenCredentialsAreWrong () {
        String username = "standard_user";
        String password = "invalidpassword";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("The message should be: Epic sadface: Username and password do not match any user in this service")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h3[data-test='error']"),
                        "Epic sadface: Username and password do not match any user in this service"));
    }
    @Test
    public void VerifyErroeIsDisplayedWhenUserIsLocked () {
        String username = "locked_out_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        wait
                .withMessage("The message should be: Epic sadface: Epic sadface: Sorry, this user has been locked out.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h3[data-test='error']"),
                        "Epic sadface: Sorry, this user has been locked out."));
    }
    @Test
    public void VerifySuccessfulLogin () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");

        driver.findElement(By.id("react-burger-menu-btn")).click();

        wait
                .withMessage("Menu is not visiblee")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));

        driver.findElement(By.cssSelector("a#logout_sidebar_link")).click();
        wait
                .withMessage("Login form is not visible after logout")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("login_container")));

    }
    @Test
    public void addingProductsToCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        wait
                .withMessage("Remove button is not visible")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));

        int productNumberInCartOnStart = 0;
        Assert.assertEquals(Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText()),
                productNumberInCartOnStart + 1,
                "The number of items in the cart did not change!");
    }
    @Test
    public void viewingProductDetails(){
            String username = "standard_user";
            String password = "secret_sauce";

            driver.findElement(By.id("user-name")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login-button")).click();

            Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                    "Should be redirected to inventory page after login");
            wait
                    .withMessage("Sauce Backpack is not on the page")
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            "//*[text()='Sauce Labs Backpack']"))).click();

        wait
                .withMessage("There is no inventory details")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_desc_container")));

        Assert.assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed(),
                "There is no inventory name");
        Assert.assertTrue(driver.findElement(By.className("inventory_details_desc")).isDisplayed(),
                "There is no inventory description");
        Assert.assertTrue(driver.findElement(By.className("inventory_details_price")).isDisplayed(),
                "There is no inventory price");
        Assert.assertTrue(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed());
    }
    @Test
    public void removingProductsFromCart () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");
        wait
                .withMessage("Sauce Backpack is not on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//*[text()='Sauce Labs Backpack']")));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("The number of products in the cart has not changed")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("shopping_cart_badge"), 1));

        driver.findElement(By.className("shopping_cart_link")).click();

        wait
                .withMessage("Product 'Sauce Labs Backpack' is not present in the cart")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.className("inventory_item_name"),
                        "Sauce Labs Backpack"));

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        wait
                .withMessage("The product 'Sauce Labs Backpack' is not deleteed from cart")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("shopping_cart_badge"), 0));
    }
    @Test
    public void productCheckout () {
        String username = "standard_user";
        String password = "secret_sauce";
        String name = "Pera";
        String lastName = "Peric";
        String cdeckoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");
        wait
                .withMessage("Sauce Backpack is not on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                        "//*[text()='Sauce Labs Backpack']")));

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("The number of products in the cart has not changed")
                .until(ExpectedConditions.numberOfElementsToBe(By.className("shopping_cart_badge"), 1));

        driver.findElement(By.className("shopping_cart_link")).click();

        wait
                .withMessage("Cart page did not open")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout"))).click();

        wait
                .withMessage("Checkout form is not present")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_info_container")));

        driver.findElement(By.id("first-name")).sendKeys(name);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(cdeckoutZip);
        driver.findElement(By.id("continue")).click();
        wait
                .withMessage("Checkout overview is not visible on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_summary_container")));

        wait
                .withMessage("Checkout overview is not visible on the page")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("checkout_summary_container")));

        Assert.assertEquals(driver.findElement(By.className("cart_quantity")).getText(),"1", "Quantity is not 1");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),"Sauce Labs Backpack", "There is no Sauce Labs item");
        Assert.assertEquals(driver.findElement(By.className("inventory_item_price")).getText(), "$29.99", "Price is not 29.99");

        Assert.assertEquals(driver.findElement(By.className("summary_subtotal_label")).getText(), "Item total: $29.99", "Subtotal is not 29.99");

        driver.findElement(By.id("finish")).click();
        wait
                .withMessage("There is no thank you screen")
                .until(ExpectedConditions.presenceOfElementLocated(By.className("complete-header")));

        Assert.assertEquals(driver.findElement(By.className("complete-header")).getText(), "Thank you for your order!", "There is no Thank you message");
    }
    @Test
    public void ValidateSocialLinksInFooter () throws IOException {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");

        WebElement footer = driver.findElement(By.className("footer"));
        new Actions(driver)
                .scrollToElement(footer)
                .perform();
        String twitterUrl = driver.findElement(By.cssSelector(".social_twitter>a")).getAttribute("href");
        String facebookUrl=driver.findElement(By.cssSelector(".social_facebook>a")).getAttribute("href");
        String linkedinUrl = driver.findElement(By.cssSelector(".social_linkedin>a")).getAttribute("href");

        int statusFacebook = getHTTPResponseStatusCode(facebookUrl);
        int statusLinkedin = getHTTPResponseStatusCode(linkedinUrl);
        int statusTwitter =  getHTTPResponseStatusCode(twitterUrl);


        Assert.assertEquals(statusFacebook, 200, "Status code for Facebook is not 200");
        Assert.assertEquals(statusTwitter, 200, "Status code for Twitter is not 200");
        Assert.assertEquals(statusLinkedin, 200, "Status code for Linkedin is not 200");
    }
    @Test
    public void TestDefaultNameSortA_Z () throws IOException {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");

        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> productsNames = new ArrayList<>();

        for (int i = 0; i < productElements.size(); i++) {
            productsNames.add(productElements.get(i).getText());
        }

            List<String> sortedProductsNames = new ArrayList<>(productsNames);
            Collections.sort(sortedProductsNames);

    }
    @Test
    public void TestInvertNamedSortZ_A () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");

        List<WebElement> productElements = driver.findElements(By.className("inventory_item_name"));
        List<String> productsNames = new ArrayList<>();

        for (int i = 0; i < productElements.size(); i++) {
            productsNames.add(productElements.get(i).getText());
        }

        List<String> reverseSortedProductsNames = new ArrayList<>(productsNames);
        Collections.sort(reverseSortedProductsNames, Collections.reverseOrder());

    }
    @Test
    public void TestSortPriceLowHigh () {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), baseURl + "inventory.html",
                "Should be redirected to inventory page after login");

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> productPrices = new ArrayList<>();

        for (int i = 0; i < priceElements.size(); i++) {
            String priceText = priceElements.get(i).getText().replace("$", "");
            double price = Double.parseDouble(priceText);
            productPrices.add(price);
        }
        List<Double> sortedProductPrices = new ArrayList<>(productPrices);
        Collections.sort(sortedProductPrices);

    }
    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        js.executeScript("window.localStorage.clear();");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

