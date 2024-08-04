package com.jambocloud.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public TestBase() {}

    public static void initialization() {
        //Set the path to the ChromeDriver
        //TODO: get this value from config file
        System.setProperty("webdriver.chrome.driver", "/Users/User/.m2/repository/org/seleniumhq/selenium/selenium-chrome-driver/4.17.0");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.get("https://jambo-cloud.example.com");

    }
}
