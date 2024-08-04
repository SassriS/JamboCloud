package com.jambocloud.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public TestBase(){

    }


    public static void initialization() {
        //Set the path to the ChromeDriver
        //TODO: get this value from config file
        System.setProperty("webdriver.chrome.driver", "/Users/User/.m2/repository/org/seleniumhq/selenium/selenium-chrome-driver/4.17.0");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://jambo-cloud.example.com");

    }
}
