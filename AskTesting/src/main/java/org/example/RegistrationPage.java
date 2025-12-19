package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    private By userNameField = By.id("reg_username");
    private By emailField = By.id("reg_email");
    private By passWordField = By.id("reg_password");
    private By submitField = By.name("register");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserName(String username) {
        driver.findElement(userNameField).sendKeys(username);

    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);

    }

    public void setPassword(String password) {
        driver.findElement(passWordField).sendKeys(password);

    }

    public AccountPage clickSubmit() {
        driver.findElement(submitField).click();
        return new AccountPage(driver);

    }
}
