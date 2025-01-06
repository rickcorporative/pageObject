package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.example.GoogleSearchPage;

import java.time.Duration;
import java.util.Scanner;

public class GoogleSearchTest {
    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;

    // Метод для настройки перед каждым тестом
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        googleSearchPage = new GoogleSearchPage(driver);
    }

    @Test
    public void testSearchResultsDisplayed() {

        googleSearchPage.open();
        googleSearchPage.enterSearchQuery("Java");
        googleSearchPage.clickSearch();

        Assert.assertTrue(googleSearchPage.isSearchResultsDisplayed(), "Результаты поиска не отображены!");

        System.out.println("The end of the GoogleSearchTest");
    }

    // Метод для завершения работы браузера после каждого теста
    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
