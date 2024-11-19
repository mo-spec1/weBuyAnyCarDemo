package com.weBuyAnyCar.utility;

import com.weBuyAnyCar.commons.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Utility extends Driver {

    Actions actions = new Actions(driver);

    public void waitForPageToLoad(){
        JavascriptExecutor j = (JavascriptExecutor)driver;
        if (j.executeScript("return document.readyState").toString().equals("complete")){
        }
    }

    public void waitUntilElementIsVisible(By element) {
        new WebDriverWait(driver, 10).until
                (ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public void waitUntilElementIsClickable(By element) {
        new WebDriverWait(driver, 50).until
                (ExpectedConditions.elementToBeClickable(element));
    }

    public void click(By element) {
        driver.findElement(element).click();
    }

    public void enterText(By element, String text) {
        new WebDriverWait(driver, 20).until(
                ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).sendKeys(text);
    }

    public void sendKeys (final String value){
        Actions actions = new Actions(driver);
        actions.sendKeys(value).build().perform();
    }

    public void clear(By element) {
        driver.findElement(element).click();
        driver.findElement(element).clear();
        driver.findElement(element).click();
    }

    public String getText(By element) {
        String value = driver.findElement(element).getText();
        return value;
    }

    public boolean elementPresent(By element) {
        if (driver.findElements(element).size() > 0) {
            try {
                if (driver.findElement(element).isDisplayed()) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public String mileageGenerator() {
        Random random = new Random();
        int value = 15000 + random.nextInt(70001);
        return String.valueOf(value);
    }

    public void sleep(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
