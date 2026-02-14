package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegressionTests extends BaseTest {

    private static final String USER = "standard_user";
    private static final String PASS = "secret_sauce";

    @Test
    public void regression_multipleItemsCheckout() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.addItemByIndex(0).addItemByIndex(1).addItemByIndex(2);
        Assert.assertEquals(products.cartBadgeCount(), 3);

        CartPage cart = products.openCart();
        Assert.assertEquals(cart.cartItemsCount(), 3);

        CheckoutOverviewPage overview = cart.checkout()
                .fill("Ali", "Test", "010000")
                .continueCheckout();

        CheckoutCompletePage complete = overview.finish();
        Assert.assertTrue(complete.getHeader().toLowerCase().contains("thank you"));
    }

    @Test
    public void regression_continueShoppingFromCart() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.addItemByIndex(0);

        CartPage cart = products.openCart();
        ProductsPage back = cart.continueShopping();

        Assert.assertTrue(back.itemsCount() > 0);
        Assert.assertEquals(back.cartBadgeCount(), 1);
    }

    @Test
    public void regression_sortingAtoZDoesNotBreakPage() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.sortByVisibleText("Name (A to Z)");
        Assert.assertTrue(products.itemsCount() > 0);
    }

    @Test
    public void regression_sortingPriceLowToHighDoesNotBreakPage() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.sortByVisibleText("Price (low to high)");
        Assert.assertTrue(products.itemsCount() > 0);
    }

    @Test
    public void regression_overviewTotalsAreVisible() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.addItemByIndex(0).addItemByIndex(1);

        CheckoutOverviewPage overview = products.openCart()
                .checkout()
                .fill("A", "B", "12345")
                .continueCheckout();

        Assert.assertTrue(overview.getItemTotalText().toLowerCase().contains("item total"));
        Assert.assertTrue(overview.getTaxText().toLowerCase().contains("tax"));
        Assert.assertTrue(overview.getTotalText().toLowerCase().contains("total"));
    }
}
