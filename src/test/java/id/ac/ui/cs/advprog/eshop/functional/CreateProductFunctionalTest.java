package id.ac.ui.cs.advprog.eshop.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)

class CreateProductFunctionalTest {
    private int serverPort;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + serverPort;
    }
    
    @Test
    void testCreateProduct_Successful(ChromeDriver driver) throws Exception {
        // Test: Create a new product with valid name and quantity
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Bacon Pancakes");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("123");

        // Expected: Product should be created and visible in product list
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());
        WebElement productTable = driver.findElement(By.tagName("table"));
        String pageContent = productTable.getText();
        assertTrue(pageContent.contains("Bacon Pancakes"));
        assertTrue(pageContent.contains("123"));
    }

    @Test
    void testCreateProduct_InvalidQuantity(ChromeDriver driver) throws Exception {
        // Test: Create a new product with invalid quantity
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("Bacon Pancakes");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("invalid");

        // Expected: Product should not be created and error message should be shown
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        assertEquals(baseUrl + "/product/create", driver.getCurrentUrl());
        WebElement errorMessage = driver.findElement(By.cssSelector("div.alert-danger"));
        assertEquals("Invalid quantity", errorMessage.getText());
    }
    @Test
    void testCreateProduct_emptyNameError(ChromeDriver driver) throws Exception {
        // Test: Create a new product with empty name
        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys("");

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys("123");

        // Expected: Product should not be created and error message should be shown
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        assertEquals(baseUrl + "/product/create", driver.getCurrentUrl());
        WebElement errorMessage = driver.findElement(By.cssSelector("div.alert-danger"));
        assertEquals("Name cannot be empty", errorMessage.getText());
    }

    
}
