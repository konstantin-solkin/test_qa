package com.qa.test.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница Авторизации
 */
public class LoginPage {

    private final SelenideElement emailFld = $("#ap_email");
    private final SelenideElement continueBtn = $("#continue");
    private final SelenideElement passwordFld = $("#ap_password");
    private final SelenideElement signInSubmitBtn = $("#signInSubmit");


    @Step("Авторизоваться, используя e-mail {mail}")
    public void logIn(String mail, String password) {
        emailFld.val(mail);
        continueBtn.click();
        passwordFld.val(password);
        Selenide.sleep(3000); // Явные ожидания зло, но в данном случае без него можно нарваться на капчу
        signInSubmitBtn.click();
    }
}
