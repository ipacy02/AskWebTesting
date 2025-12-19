package checkout;

import BaseTests.BaseTest;
import org.example.CartPage;
import org.example.CheckOutPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class checkoutTest extends BaseTest {

    @Test
    void testCheckout() {
        CartPage cartPage = homePage.clickViewLink();
        // Go to checkout
        cartPage.goToCheckout();

        CheckOutPage checkOutPage = new CheckOutPage(driver);

        // Fill all required fields
        checkOutPage.setFirstNameField("Rukundo");
        checkOutPage.setLastNameField("Martin");
        checkOutPage.setCompanyField("Martin Enterprise");
        checkOutPage.setDepartment("IT");
        checkOutPage.setCountryField("Uruguay");
        checkOutPage.setStreetField("Mibirizi");
        checkOutPage.setCityField("Panama");
        checkOutPage.setStateField("California");
        checkOutPage.setPostcode("00000");
        checkOutPage.setPhoneField("0868234");
        checkOutPage.setEmailField("rukundo@gmail.com");

        // Create account fields
        checkOutPage.setCreateAccount(true);
        checkOutPage.setAccountUsername("rukundo123");
        checkOutPage.setAccountPassword("Password@123");

        checkOutPage.setOrderComment("We are good to go");

        // Place order
        checkOutPage.clickPlaceOrder();

        // Assert order confirmation
        assertEquals(checkOutPage.getOrderConfirmationMessage(), "Checkout", "No confirmation message found");
    }
}
