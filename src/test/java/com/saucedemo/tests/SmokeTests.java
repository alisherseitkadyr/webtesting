package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTests extends BaseTest {

    private static final String USER = "standard_user";
    private static final String PASS = "secret_sauce";

    @Test
    public void smoke_loginWorks() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        Assert.assertTrue(products.getTitle().toLowerCase().contains("products"));
        Assert.assertTrue(products.itemsCount() > 0);
    }

    @Test
    public void smoke_addToCartWorks() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.addItemByIndex(0);
        Assert.assertEquals(products.cartBadgeCount(), 1);
    }

    @Test
    public void smoke_removeFromCartWorks() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.addItemByIndex(0);
        Assert.assertEquals(products.cartBadgeCount(), 1);
        products.removeItemByIndex(0);
        Assert.assertEquals(products.cartBadgeCount(), 0);
    }

    @Test
    public void smoke_checkoutFinishWorks() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        products.addItemByIndex(0);

        CartPage cart = products.openCart();
        Assert.assertEquals(cart.cartItemsCount(), 1);

        CheckoutStepOnePage step1 = cart.checkout();
        CheckoutOverviewPage overview = step1.fill("Alisher", "QA", "050000").continueCheckout();

        CheckoutCompletePage complete = overview.finish();
        Assert.assertTrue(complete.getHeader().toLowerCase().contains("thank you"));
    }

    @Test
    public void smoke_logoutWorks() {
        ProductsPage products = loginPage().loginValid(USER, PASS);
        LoginPage back = products.logout();
        // Just verify we can see login error on invalid attempt => means we are on login page
        back.loginInvalid("x", "y");
        Assert.assertTrue(back.getErrorText().toLowerCase().contains("username and password"));
    }
}
