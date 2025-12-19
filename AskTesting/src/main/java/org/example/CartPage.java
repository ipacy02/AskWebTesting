package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By qtyInput = By.cssSelector("input.qty");
    private By updateCartBtn = By.cssSelector("button[name='update_cart']");
    private By removeBtn = By.cssSelector("a.remove");
    private By overlay = By.cssSelector("div.blockUI.blockOverlay");
    private By proceedToCheckoutBtn = By.cssSelector("a.checkout-button");
    private By checkoutForm = By.cssSelector("form.checkout");
    private By placeOrderBtn = By.id("place_order");

    public void increaseQuantity(int times) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(qtyInput));
        input.click();
        for (int i = 0; i < times; i++) {
            input.sendKeys(Keys.ARROW_UP);
        }
    }

    public void decreaseQuantity(int times) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(qtyInput));
        input.click();
        for (int i = 0; i < times; i++) {
            input.sendKeys(Keys.ARROW_DOWN);
        }
    }

    public void clickUpdateCartAndWait(int oldQty) {
        wait.until(ExpectedConditions.elementToBeClickable(updateCartBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        wait.until((ExpectedCondition<Boolean>) d -> {
            int currentQty = Integer.parseInt(d.findElement(qtyInput).getAttribute("value"));
            return currentQty != oldQty;
        });
    }

    public int getQuantity() {
        return Integer.parseInt(
                wait.until(ExpectedConditions.visibilityOfElementLocated(qtyInput))
                        .getAttribute("value")
        );
    }

    public void removeItem() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
        remove.click();
        wait.until(ExpectedConditions.stalenessOf(remove));
    }

    // Navigate to checkout
    public void goToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutForm));
    }
}
