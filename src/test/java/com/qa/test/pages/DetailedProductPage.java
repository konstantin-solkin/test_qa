package com.qa.test.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница с детальной информацией о товаре
 */
public class DetailedProductPage {

    private final SelenideElement addToCartBtn = $("#add-to-cart-button");

    @Step("Добавить товар в корзину")
    public void addToBasket() {
        addToCartBtn.click();
    }

}
