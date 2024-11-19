package com.weBuyAnyCar.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                strict = true,
                features = {"src/test/java/com/weBuyAnyCar/features"},
                plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
                glue = {"com/weBuyAnyCar/hooks", "com/weBuyAnyCar/stepDefinitions"},
                tags = {"(@Regression) and not @AcceptanceTests"}
        )
public class TestRunner {
}
