package com.weBuyAnyCar.pages;

import com.weBuyAnyCar.commons.CarDetailsHolder;
import com.weBuyAnyCar.utility.Utility;
import com.weBuyAnyCar.commons.PropertyReader;
import com.weBuyAnyCar.commons.TextFileReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.List;
import java.util.Map;

public class BasePage extends Utility {

    private static final By ACCEPT_ALL_COOKIES = By.xpath("//button[contains(@id,'onetrust-accept')]");
    private static final By VEHICLE_LOOK_UP_FIELD = By.xpath("//input[@aria-label='Vehicle lookup']");
    private static final By MILEAGE_TEXT_FIELD = By.xpath("//input[@aria-label='Mileage']");
    private static final By GET_CAR_VALUATION_BTTN = By.xpath("//button[@id='btn-go']");
    private static final By CAR_REG = By.xpath("(//div[contains(@class,'details-vrm ng-star-inserted')])[2]");
    private static final By CAR_MAKE = By.xpath("(//div[contains(@class,'d-table-cell value')])[8]");
    private static final By CAR_MODEL = By.xpath("(//div[contains(@class,'d-table-cell value')])[9]");
    private static final By CAR_YEAR = By.xpath("(//div[contains(@class,'d-table-cell value')])[10]");
    private static final By VALUATION_BACK_BTTN = By.xpath("//button[@id='btn-back']");
    private static final By REG_NOT_FOUND_ERROR = By.xpath("//div[@class='col-12 col-lg-12 offset-lg-0 page-header']");
    private static final By MANUAL_FORM_ERROR = By.xpath("//dynamic-form-question[@class='inline-label']");

    public CarDetailsHolder getCarDetailsFromResultsPage(){
        String reg = getText(CAR_REG).trim();
        String make = getText(CAR_MAKE).trim();
        String model = getText(CAR_MODEL).trim();
        String year = getText(CAR_YEAR).trim();

        String formatReg = reg + "";
        String formatMake = make + "";
        String formatModel = model + "";
        String formatYear = year + "";
        return new CarDetailsHolder(formatReg, formatMake, formatModel, formatYear);
    }

    boolean isValid = true;
    private List<String> regNumbers;
    private String currentRegNumber;
    private Map<String, CarDetailsHolder> expectedCarDetailsMap;
    String regex = "\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b";
    String filePath = getProperty("InputFilePath");

    public String getProperty(String property) {
        String data = new PropertyReader().readProperty(property);
        return data;
    }

    public void goToWebpage(){
        driver.navigate().to(getProperty("WBAC"));
    }

    public void acceptCookies(){
        waitForPageToLoad();
        waitUntilElementIsClickable(ACCEPT_ALL_COOKIES);
        click(ACCEPT_ALL_COOKIES);
    }

    public void getRegNumberAndSerachReg(String currentRegNumber){
        waitUntilElementIsClickable(VEHICLE_LOOK_UP_FIELD);

        regNumbers = TextFileReader.retrieveRegNumbers(filePath, regex);
        for (int i = 0; i < regNumbers.size(); i++) {
            regNumbers.set(i, regNumbers.get(i).trim().replaceAll("\\s+", ""));
        }

        for (String regNumber : regNumbers) {
            try {
                currentRegNumber = regNumber;
                System.out.println("Searching for registration number: " + currentRegNumber);

                click(VEHICLE_LOOK_UP_FIELD);
                enterText(VEHICLE_LOOK_UP_FIELD, regNumber);
                click(MILEAGE_TEXT_FIELD);
                sendKeys(mileageGenerator());
                click(GET_CAR_VALUATION_BTTN);
                waitForPageToLoad();
                sleep(4);
                if (!elementPresent(REG_NOT_FOUND_ERROR)) {
                    if (elementPresent(MANUAL_FORM_ERROR)){
                        driver.navigate().back();
                        System.err.println("Error: Registration number not found. Proceeding with the next registration");
                        isValid = false;
                    }
                    else {
                        try {
                            waitUntilElementIsVisible(CAR_REG);
                        } catch (TimeoutException e) {
                            System.err.println("Timeout waiting for Car details. Proceeding with the next steps");
                        }
                    }
                } else if (elementPresent(REG_NOT_FOUND_ERROR)) {
                    System.err.println("Error: Registration number not found. Proceeding with the next registration");
                    isValid = false;
                }

                if (expectedCarDetailsMap == null) {
                    expectedCarDetailsMap = TextFileReader.retrieveCarDetails(getProperty("OutputFilePath"));
                }

                CarDetailsHolder actualDetails = getCarDetailsFromResultsPage();
                CarDetailsHolder expectedDetails = expectedCarDetailsMap.get(currentRegNumber);

                if (expectedCarDetailsMap == null) {
                    Assert.fail();
                }

                Assert.assertEquals("Reg match " + currentRegNumber, expectedDetails.getReg(), actualDetails.getReg());
                Assert.assertEquals("Make match " + currentRegNumber, expectedDetails.getMake(), actualDetails.getMake());
                Assert.assertEquals("Model match " + currentRegNumber, expectedDetails.getModel(), actualDetails.getModel());
                Assert.assertEquals("Year match " + currentRegNumber, expectedDetails.getYear(), actualDetails.getYear());
            }
            catch (Exception e){
                System.err.println("Error with reg number:" + regNumber + e.getMessage());
                e.printStackTrace();
            }
            if (elementPresent (VALUATION_BACK_BTTN)) {
                click(VALUATION_BACK_BTTN);
            }
            clear(VEHICLE_LOOK_UP_FIELD);
            clear(MILEAGE_TEXT_FIELD);
        }
    }
}
