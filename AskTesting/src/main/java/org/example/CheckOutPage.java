package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---------- LOCATORS ----------
    private By firstNameField = By.id("billing_first_name");
    private By lastNameField = By.id("billing_last_name");
    private By companyField = By.id("billing_company");
    private By departmentDropdown = By.id("billing_department"); // adjust ID if needed
    private By countryDropdown = By.id("select2-billing_country-container");
    private By countrySearchInput = By.cssSelector("input.select2-search__field");
    private By streetField = By.name("billing_address_1");
    private By cityField = By.id("billing_city");
    private By stateDropdown = By.id("select2-billing_state-container");
    private By stateSearchInput = By.cssSelector("input.select2-search__field");
    private By postcodeField = By.id("billing_postcode");
    private By phoneField = By.id("billing_phone");
    private By emailField = By.id("billing_email");
    private By createAccountCheckbox = By.id("createaccount");
    private By accountUsernameField = By.id("account_username");
    private By accountPasswordField = By.id("account_password");
    private By orderComment = By.id("order_comments");
    private By placeOrderButton = By.id("place_order");
    private By orderConfirmationMsg = By.cssSelector("p.woocommerce-thankyou-order-received");

    // ---------- SETTERS ----------
    public void setFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setCompanyField(String companyName) {
        driver.findElement(companyField).sendKeys(companyName);
    }

    public void setDepartment(String department) {
        WebElement dropdown = driver.findElement(departmentDropdown);
        dropdown.click();
        dropdown.sendKeys(department);
        dropdown.sendKeys(Keys.ENTER);
    }

    public void setCountryField(String country) {
        driver.findElement(countryDropdown).click();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(countrySearchInput));
        input.sendKeys(country);
        input.sendKeys(Keys.ENTER);
    }

    public void setStreetField(String street) {
        driver.findElement(streetField).sendKeys(street);
    }

    public void setCityField(String city) {
        driver.findElement(cityField).sendKeys(city);
    }

    public void setStateField(String stateName) {
        driver.findElement(stateDropdown).click();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(stateSearchInput));
        input.sendKeys(stateName);
        input.sendKeys(Keys.ENTER);
    }

    public void setPostcode(String postcode) {
        driver.findElement(postcodeField).sendKeys(postcode);
    }

    public void setPhoneField(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setCreateAccount(boolean create) {
        WebElement checkbox = driver.findElement(createAccountCheckbox);
        if (create && !checkbox.isSelected()) checkbox.click();
        if (!create && checkbox.isSelected()) checkbox.click();
    }

    public void setAccountUsername(String username) {
        driver.findElement(accountUsernameField).sendKeys(username);
    }

    public void setAccountPassword(String password) {
        driver.findElement(accountPasswordField).sendKeys(password);
    }

    public void setOrderComment(String comment) {
        driver.findElement(orderComment).sendKeys(comment);
    }

    // ---------- ACTIONS ----------
    public void clickPlaceOrder() {
        driver.findElement(placeOrderButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmationMsg));
    }

    public String getOrderConfirmationMessage() {
        return driver.findElement(orderConfirmationMsg).getText();
    }
}
