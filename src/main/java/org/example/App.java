package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\programming\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите поисковое слово: ");
        String searchQuery = scanner.nextLine();


        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchQuery);

        WebElement searchButton = driver.findElement(By.name("btnK"));

        Thread.sleep(1000);

        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

        WebElement searchResults = driver.findElement(By.id("search"));
        if (searchResults.isDisplayed()) {
            System.out.println("Результаты поиска успешно прогрузились!");
        } else {
            System.out.println("Результаты поиска не прогрузились.");
        }

        List<WebElement> searchResultsList = driver.findElements(By.cssSelector("div#search h3"));
        System.out.println("Найдено результатов: " + searchResultsList.size());

        boolean allResultsContainKeyword = true;
        for (WebElement result : searchResultsList) {
            String resultText = result.getText();
            System.out.println("Результат: " + resultText);

            if (!resultText.toLowerCase().contains(searchQuery.toLowerCase())) {
                System.out.println("Ключевое слово отсутствует в результате: " + resultText);
                allResultsContainKeyword = false;
            }
        }

        if (allResultsContainKeyword) {
            System.out.println("Все результаты содержат ключевое слово.");
        } else {
            System.out.println("Не все результаты содержат ключевое слово.");
        }


        Thread.sleep(3000);

        driver.quit();
    }
}
