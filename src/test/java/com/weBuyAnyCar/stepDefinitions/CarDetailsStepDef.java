package com.weBuyAnyCar.stepDefinitions;

import com.weBuyAnyCar.pages.BasePage;
import com.weBuyAnyCar.commons.PropertyReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

public class CarDetailsStepDef extends BasePage {

    PropertyReader propertyReader = PageFactory.initElements(driver, PropertyReader.class);
    BasePage basePage = PageFactory.initElements(driver, BasePage.class);

    @Given("User Navigates to the weBuyAnyCar Webpage")
    public void userNavigatesToTheWeBuyAnyCarWebpage() {
        basePage.goToWebpage();
    }

    @Then("User enters the {string} Number and validates Car details")
    public void enterCarRegNumberAndSearch(String registrationNo) {
        basePage.acceptCookies();
        basePage.getRegNumberAndSerachReg(registrationNo);
    }
}
