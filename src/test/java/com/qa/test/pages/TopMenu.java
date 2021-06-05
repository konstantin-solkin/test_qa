package com.qa.test.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

/**
 * Верхнее меню
 */
public class TopMenu {

    private final SelenideElement searchInput = $("#twotabsearchtextbox");
    private final SelenideElement searchSubmitBtn = $("#nav-search-submit-button");

    private final SelenideElement loginArea = $("#nav-link-accountList");

    private final SelenideElement cartCount = $("#nav-cart-count");

    // Your account menu
    private final SelenideElement yourAccountArea = $("#nav-al-your-account");
    private final SelenideElement signOutBtn = yourAccountArea.$(byText("Sign Out"));

    // Actions

    @Step("Перейти на страницу авторизации")
    public LoginPage goToLoginPage() {
        loginArea.click();
        return new LoginPage();
    }

    @Step("Выйти из аккаунта")
    public LoginPage logOut() {
        loginArea.hover();
        signOutBtn.click();
        return new LoginPage();
    }

    @Step("Перейти в корзину")
    public CartPage goToCartPage() {
        cartCount.click();
        return new CartPage();
    }

    @Step("Ввести в строку поиска текст: {text}")
    public void enterSearchTextAndSubmit(String text) {
        searchInput.val(text);
        searchSubmitBtn.click();
    }

    // Verifications

    @Step("Проверить, что в корзину добавлено {val} товаров")
    public void shouldCartCountIs(int val) {
        cartCount.shouldBe(Condition.text(String.valueOf(val)));
    }

}
