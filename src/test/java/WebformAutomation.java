import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebformAutomation {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @DisplayName("Test Webform Submission")
    @Test
    public void testWebformSubmission() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");

        fillFormField("edit-name", "John1 Doe");
        fillFormField("edit-number", "12345678880");

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        fillFormField("edit-date", currentDate);

        fillFormField("edit-email", "johndoe1@example.com");
        fillFormField("edit-tell-us-a-bit-about-yourself-", "This is a test submission.");

        String filePath = Paths.get("G:\\Downloads\\Thesis_report_defense__49_main.pdf").toAbsolutePath().toString();
        WebElement fileInput = driver.findElement(By.id("edit-uploadocument-upload"));
        fileInput.sendKeys(filePath);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".file--mime-application-pdf")));

        clickElement(By.id("edit-age"));
        clickElement(By.id("edit-submit"));

        try {
            // Update the selector to match the success message location
            WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#block-pagetitle-2 h1")));
            assertTrue(successMessage.isDisplayed(), "Success message is not displayed");
            assertEquals("Thank you for your submission!", successMessage.getText(), "Expected success message was not found.");
            System.out.println("Success message: " + successMessage.getText());
        } catch (TimeoutException e) {
            System.out.println("Success message not found. Checking for other messages...");

            List<WebElement> allMessages = driver.findElements(By.cssSelector(".messages"));
            if (!allMessages.isEmpty()) {
                for (WebElement message : allMessages) {
                    System.out.println("Message found: " + message.getText());
                }
            } else {
                System.out.println("No messages found. Current page title: " + driver.getTitle());
                System.out.println("Current URL: " + driver.getCurrentUrl());
                System.out.println("Page source:");
                System.out.println(driver.getPageSource());
            }
            throw new AssertionError("Form submission failed or unexpected result", e);
        }
    }

    private void fillFormField(String id, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.clear();
        element.sendKeys(value);
    }

    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
