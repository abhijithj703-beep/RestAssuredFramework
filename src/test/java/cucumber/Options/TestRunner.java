package cucumber.Options;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectFile;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
// Use SelectFile when the feature file is under src/test/java
@SelectFile("src/test/java/features/placeValidations.feature")

// 👇 IMPORTANT: this must match the *package* where StepDefinition.java lives
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions")

@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "pretty,json:target/jsonReports/cucumber-report.json,html:target/cucumber-reports.html"
)
//@IncludeTags("DeletePlace") 
public class TestRunner { 
	
}
