package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",           // dove hai i .feature
        glue = {"stepdefinitions", "hooks"},                // package delle step e degli hooks
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        monochrome = true,
        tags = "@Test_001"      // così lancia solo questo scenario
)
public class RunCucumberTest {
}
