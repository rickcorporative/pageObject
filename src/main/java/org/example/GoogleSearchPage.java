package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleSearchPage {
    private WebDriver driver;

    private By searchBox = By.name("q");
    private By searchButton = By.name("btnK");
    private By searchResultsContainer = By.id("search");
    private By searchResultsTitles = By.cssSelector("div#search h3");

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.google.com");
    }

    public void enterSearchQuery(String query) {
        driver.findElement(searchBox).sendKeys(query);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public boolean isSearchResultsDisplayed() {
        WebElement container = driver.findElement(searchResultsContainer);
        return container.isDisplayed();
    }

    public List<WebElement> getSearchResultsTitles() {
        return driver.findElements(searchResultsTitles);
    }
}