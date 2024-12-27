package org.example;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.GoogleSearchPage;

import java.time.Duration;
import java.util.Scanner;

public class GoogleSearchTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\programming\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите поисковое слово: ");
        String searchQuery = scanner.nextLine();

        try {
            googleSearchPage.open();

            googleSearchPage.enterSearchQuery(searchQuery);
            Thread.sleep(1000);
            googleSearchPage.clickSearch();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(driver1 -> googleSearchPage.isSearchResultsDisplayed());

            System.out.println("Результаты поиска успешно прогрузились!");

            // Проверяем заголовки результатов поиска
            var searchResultsList = googleSearchPage.getSearchResultsTitles();
            System.out.println("Найдено результатов: " + searchResultsList.size());

            boolean allResultsContainKeyword = true;
            for (var result : searchResultsList) {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
