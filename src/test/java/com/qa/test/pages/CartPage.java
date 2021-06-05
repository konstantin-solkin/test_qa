package com.qa.test.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница "Корзина"
 */
public class CartPage {

    private final ElementsCollection deleteButtons = $$("[data-action=delete] input");
    private final SelenideElement basketIsEmptyText = $x("//*[contains(text(), 'asket is empty')]");

    @Step("Удалить товар из корзины")
    public void deleteItemFromBasketByIndex(int index) {
        deleteButtons.filter(Condition.visible).get(index).click();
    }

    @Step("Проверить, что корзина пуста")
    public void shouldBasketIsEmpty() {
        basketIsEmptyText.shouldBe(Condition.visible);
        deleteButtons.shouldBe(CollectionCondition.size(0));
    }

}
