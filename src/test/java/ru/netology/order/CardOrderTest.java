package ru.netology.order;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardOrderTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSubmitFormWithSimpleName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Petr Petrov");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79991112223");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldSubmitFormWithDoubleName() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Анна-Мария Иванова");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79991112223");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldSubmitFormWithDoubleSurname() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Анна Иванова-Петрова");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79991112223");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldSubmitFormWithDoubleNameAndSurname() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Анна-Мария Иванова-Петрова");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79991112223");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }


}