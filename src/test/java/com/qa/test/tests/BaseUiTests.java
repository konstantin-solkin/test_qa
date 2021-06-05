package com.qa.test.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public abstract class BaseUiTests {

    @BeforeSuite
    void setUp() {
        Configuration.fastSetValue = false;
        Configuration.timeout = 12000;
        Configuration.browserSize = "1366x768";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.startMaximized = false;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    @BeforeMethod
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterMethod
    void cleanUpAfterEveryTest() {
        Selenide.closeWebDriver();
    }

}
