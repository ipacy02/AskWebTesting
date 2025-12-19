package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StorePage {

    private WebDriver driver;
    protected WebDriverWait wait;

    public StorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //search
    private By searchField = By.id("woocommerce-product-search-field-0");
    private By submitSearch = By.cssSelector("button[value='Search']");

    public void setProduct(String word) {
        driver.findElement(searchField).sendKeys(word);
    }

    public ProductPage clickSubmit() {
        driver.findElement(submitSearch).click();
        return new ProductPage(driver);
    }

    private By categoryField = By.id("product_cat");
    private By textField = By.cssSelector("h1.woocommerce-products-header__title.page-title");


    public String selectCategory(String name) {
        driver.findElement(categoryField).click();
        new Select(driver.findElement(categoryField)).selectByValue(name);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(textField));
        System.out.println(text.getText());
        return text.getText();
    }
}
