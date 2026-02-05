package Register;

import BaseTests.BaseTest;
import org.example.AccountPage;
import org.example.RegistrationPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegisterTest extends BaseTest {

    @Test
    void testRegister() {

        RegistrationPage registrationPage = homePage.clickAccountLink();
        registrationPage.setUserName("muhu");   //add this to the repository
        registrationPage.setEmail("muhu@gmail.com");
        registrationPage.setPassword("muhuaa20");

        AccountPage accountPage = registrationPage.clickSubmit();
        assertEquals(accountPage.getMessage(), "Account");

    }
}
