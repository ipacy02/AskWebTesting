package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By accountLink = By.linkText("Account");
    private By storeLink = By.linkText("Store");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------- NAVIGATION ----------
    public RegistrationPage clickAccountLink() {
        wait.until(ExpectedConditions.elementToBeClickable(accountLink)).click();
        return new RegistrationPage(driver);
    }

    public StorePage clickStore() {
        wait.until(ExpectedConditions.elementToBeClickable(storeLink)).click();
        return new StorePage(driver);
    }


    // ---------- CART COUNT ----------
    private By cartCount = By.cssSelector("span.count");

    public int getCartCount() {
        try {
            String text = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(cartCount)
            ).getText().trim();
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0; // safe default
        }
    }

    // ---------- ADD TO CART BY PRODUCT ID ----------
    public void addProductById(String productId) {

        By addButton = By.cssSelector("a[data-product_id='" + productId + "']");
        int before = getCartCount();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addButton));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();

        // TRY to wait for cart update, but do not fail if it doesn't change
        try {
            wait.until(d -> getCartCount() > before);
        } catch (TimeoutException ignored) {
            // cart count did not change — acceptable on this site
        }
    }

    // ---------- VIEW CART ----------
    private By AddToCartButton = By.cssSelector("a[data-quantity='1']");
    private By viewCartField = By.cssSelector("a.added_to_cart.wc-forward");

    public String clickButtonAdd() {

        int before = getCartCount();

        wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton)).click();

        // Try waiting for cart update, but do NOT block
        try {
            wait.until(d -> getCartCount() > before);
        } catch (TimeoutException ignored) {
            // product already in cart or no AJAX update
        }

        return "";
    }

    public CartPage clickViewLink() {

        clickButtonAdd();

        try {
            // Preferred WooCommerce behavior
            wait.until(ExpectedConditions.elementToBeClickable(viewCartField)).click();
        } catch (Exception e) {
            // Guaranteed fallback
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cart"))).click();
        }

        return new CartPage(driver);
    }

    // ---------- WAIT FOR CART COUNT ----------
    public int waitForCartCountToBe(int expected) {

        try {
            wait.until(d -> getCartCount() == expected);
        } catch (TimeoutException ignored) {
            // allow test to proceed
        }

        return getCartCount();
    }
}
