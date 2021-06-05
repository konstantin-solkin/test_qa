package com.qa.test.tests;

import com.codeborne.selenide.Selenide;
import com.qa.test.config.ConfigHelper;
import com.qa.test.pages.CartPage;
import com.qa.test.pages.DetailedProductPage;
import com.qa.test.pages.SearchResultsPage;
import com.qa.test.pages.TopMenu;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Epic("Тестовое задание")
public class AmazonTests extends BaseUiTests {

    private final String AMAZON_URL = "https://amazon.co.uk";

    @Test(description = "Сценарий: Тестовое задание для QA веб-автоматизация")
    public void testSearchProductWithBestPrice() {
        String searchText = "iphone 11 256Gb black";
        TopMenu topMenu = new TopMenu();

        step("Открыть сайт и авторизоваться", () -> {
            open(AMAZON_URL);
            topMenu.goToLoginPage()
                    .logIn(ConfigHelper.getUserEmail(), ConfigHelper.getUserPassword());
        });

        step("Найти и добавить в корзину самый дешевый товар", () -> {
            topMenu.enterSearchTextAndSubmit(searchText);
            SearchResultsPage searchResultsPage = new SearchResultsPage();
            searchResultsPage.findAndSelectItemWithBestPrice(searchText);

            new DetailedProductPage().addToBasket();
            topMenu.shouldCartCountIs(1);
        });

        step("Удалить из корзины товар и выйти из аккаунта", () -> {
            CartPage cartPage = topMenu.goToCartPage();
            cartPage.deleteItemFromBasketByIndex(0);
            cartPage.shouldBasketIsEmpty();
            topMenu.shouldCartCountIs(0);
            topMenu.logOut();
        });

    }

    @Test(description = "Кейс 2 (для проверки многопоточки)")
    public void test2() {
        open(AMAZON_URL);
        Selenide.sleep(5000);
    }

    @Test(description = "Кейс 3 (для проверки многопоточки)")
    public void test3() {
        open(AMAZON_URL);
        Selenide.sleep(5000);
    }

}
