package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver driver;
    private By accountMessage = By.xpath("//*[@id=\"post-1235\"]/div/div[1]/div/h1");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMessage() {
        return driver.findElement(accountMessage).getText();

    }
}
