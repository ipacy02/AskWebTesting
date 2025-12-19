package addToCart;

import BaseTests.BaseTest;
import org.example.CartPage;
import org.example.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddToCartTest extends BaseTest {

    @Test
    void addUpdateAndRemoveProductFromCart() {

        HomePage homePage = new HomePage(driver);

        // ---------- ADD ----------
        int before = homePage.getCartCount();

        homePage.addProductById("1215");
        homePage.addProductById("1209");

        int afterAdd = homePage.getCartCount();
        assertEquals(afterAdd, before + 2);

        // ---------- GO TO CART ----------
        driver.get("https://askomdch.com/cart/");
        CartPage cartPage = new CartPage(driver); // create AFTER navigation

        // ---------- UPDATE ----------
        int oldQty = cartPage.getQuantity();

        cartPage.increaseQuantity(2);
        cartPage.decreaseQuantity(1);
        cartPage.clickUpdateCartAndWait(oldQty);

        int newQty = cartPage.getQuantity();
        assertEquals(newQty, oldQty + 1);

        // ---------- REMOVE ----------
        cartPage.removeItem();

        // wait-safe cart count check
        int afterRemove = homePage.waitForCartCountToBe(afterAdd - 1);
        assertEquals(afterRemove, afterAdd - 1);
    }
}
