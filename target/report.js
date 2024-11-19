$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/com/weBuyAnyCar/features/ValidateCarSpec.feature");
formatter.feature({
  "name": "Ensure Car details are returned",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "Access the weBuyAnyCar Website",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User Navigates to the weBuyAnyCar Webpage",
  "keyword": "Given "
});
formatter.match({
  "location": "com.weBuyAnyCar.stepDefinitions.CarDetailsStepDef.userNavigatesToTheWeBuyAnyCarWebpage()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Create a new device successfully",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "name": "User enters the \"Registration\" Number and validates Car details",
  "keyword": "Then "
});
formatter.match({
  "location": "com.weBuyAnyCar.stepDefinitions.CarDetailsStepDef.enterCarRegNumberAndSearch(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});