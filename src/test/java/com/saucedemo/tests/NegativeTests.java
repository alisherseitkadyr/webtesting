package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests extends BaseTest {

    @Test
    public void negative_invalidLoginShowsError() {
        loginPage().loginInvalid("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage().getErrorText().toLowerCase().contains("username and password"));
    }

    @Test
    public void negative_checkoutEmptyFirstNameShowsValidationError() {
        ProductsPage products = loginPage().loginValid("standard_user", "secret_sauce");
        products.addItemByIndex(0);

        CartPage cart = products.openCart();
        CheckoutStepOnePage step1 = cart.checkout();

        step1.fill("", "QA", "050000").continueExpectError();
        Assert.assertTrue(step1.getErrorText().toLowerCase().contains("first name"));
    }
}
