package com.weBuyAnyCar.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Browser extends Driver {
    private WebDriver initChrome()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito","--start-maximized","--disable-infobars","--disable-site-isolation-trials");
        return new ChromeDriver(options);
    }

    private WebDriver initHeadlessChrome()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless","--incognito","--window-size=1920,1080","--disable-infobars","--disable-site-isolation-trials");
        return new ChromeDriver(options);
    }

    private WebDriver initFirefox()
    {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver initHeadlessFirefox()
    {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-gpu", "--headless");
        return new FirefoxDriver(options);
    }

    public WebDriver launchBrowser (String browser)
    {
        switch (browser)
        {
            case "Chrome":
                driver = initChrome();
                break;
            case "Firefox":
                driver =  initFirefox();
                break;
            case "HeadlessChrome":
                driver = initHeadlessChrome();
                break;
            case "HeadlessFirefox":
                driver = initHeadlessFirefox();
                break;
            default:
                driver = initChrome();
                break;
        }
        return driver;
    }

    public void closeBrowser()
    {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
