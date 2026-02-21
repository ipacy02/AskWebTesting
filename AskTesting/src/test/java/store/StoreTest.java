package store;

import BaseTests.BaseTest;

import org.example.ProductPage;
import org.example.StorePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class StoreTest extends BaseTest {

    @Test
    void testStore() {
        StorePage storePage = homePage.clickStore();

        storePage.setProduct("Anchor Bracelet");
        ProductPage productPage = storePage.clickSubmit();
        assertEquals(productPage.getProductName(), "Anchor Bracelet", "Not the same");

    }

    @Test
    void testAccessoriesCategory() {
        assertEquals(homePage.clickStore().selectCategory("men"), "Men", "Not match"); //this is for men category
        assertEquals(homePage.clickStore().selectCategory("women"), "Women", "Not match");
        assertEquals(homePage.clickStore().selectCategory("mens-jeans"), "Men's Jeans", "Not match");
        assertEquals(homePage.clickStore().selectCategory("mens-shirts"), "Men's Shirts", "Not match");
        assertEquals(homePage.clickStore().selectCategory("mens-shoes"), "Men's Shoes", "Not match");
        assertEquals(homePage.clickStore().selectCategory("purses-and-handbags"), "Purses And Handbags", "Not match");
        assertEquals(homePage.clickStore().selectCategory("women"), "Women", "Not match");
        assertEquals(homePage.clickStore().selectCategory("womens-jeans"), "Women's Jeans", "Not match");
        assertEquals(homePage.clickStore().selectCategory("womens-shirts"), "Women's Shirts", "Not match");
        assertEquals(homePage.clickStore().selectCategory("womens-shoes"), "Women's Shoes", "Not match");
    }

}






