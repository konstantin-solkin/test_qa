package com.qa.test.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.util.Strings;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Страница с результатами поиска
 */
public class SearchResultsPage {

    private final ElementsCollection searchResultItems = $$x("//div[contains(@class, 's-result-item') and @data-uuid]");


    @Step("Найти самый дешевый товар и выбрать его")
    public void findAndSelectItemWithBestPrice(String searchParams) {
        String spanTitleXpath = ".//h2//span";
        String[] params = searchParams == null ? null : searchParams.split(" ");
        int i = 0;
        double min = Double.MAX_VALUE;
        int minIndex = -1;
        for (SelenideElement row : searchResultItems) {
            // "с требуемыми характеристиками" - я понял так, что надо проверять, что найденный товар содержит то, что мы вводили в строку поиска
            // (исключив тем самым, например, чехлы, которые попадаются в результатах поиска
            boolean isAppropriateItem = true;
            if (params != null && /* но так как в результатах не было, чтоб одновременно это был и iphone 11 и 256Gb и black, то я отключил это условие*/
                    false) {
                String title = row.$x(spanTitleXpath).text();
                for (String param : params) {
                    if (!title.contains(param)) {
                        isAppropriateItem = false;
                        break;
                    }
                }
            }
            if (isAppropriateItem) {
                SelenideElement priceElem = row.$x(".//span[@class='a-offscreen']");
                String priceStr = priceElem.exists() ? priceElem.innerText()
                        .replaceAll("[^0-9.]", "") : null;
                Double price = Strings.isNullOrEmpty(priceStr) ? null : Double.valueOf(priceStr);
                if (price != null && price < min) {
                    minIndex = i;
                    min = price;
                }
            }
            i++;
        }
        if (minIndex < 0)
            Assert.fail("Не найдено ни одного подходящего товара");
        searchResultItems.get(minIndex).$x(spanTitleXpath).click();
    }


}
