package com.weBuyAnyCar.hooks;

import com.weBuyAnyCar.commons.Browser;
import com.weBuyAnyCar.commons.PropertyReader;
import com.weBuyAnyCar.commons.TextFileReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.List;

public class Hooks extends Browser {
    private Scenario scenario;
    private List<String> regNo;
    private static int currentIndex =0;
    String regex = "\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b";

    public String getProperty(String property) {
        String data = new PropertyReader().readProperty(property);
        return data;
    }

    @Before
    public void setUp() {
        this.scenario = scenario;
        launchBrowser("Chrome");
    }

    @Before
    public void retrieveRegs(){
        if (regNo == null){
            regNo = TextFileReader.retrieveRegNumbers(getProperty("InputFilePath"), regex);
        }

        if (currentIndex >= regNo.size()) {
            throw new RuntimeException("No more registration numbers to process.");
        }

        String currentRegNumber = regNo.get(currentIndex).trim().replaceAll("\\s+", "");
        System.setProperty("currentRegNumber", currentRegNumber);

        currentIndex++;
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
