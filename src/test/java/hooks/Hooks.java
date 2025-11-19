package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

@Slf4j
public class Hooks {

    @Before
    public void setUp() {
        log.info("===== INIZIO SCENARIO =====");
        DriverManager.initDriver();
    }

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                // Questo metodo permetterà ad Allure di allegare uno screenshot ad ogni step
                Allure.addAttachment("Screenshot after step", new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                log.error("Failed to take screenshot after step: {}", e.getMessage());
            }
        }
    }

    @After
    public void tearDown() {
        log.info("===== FINE SCENARIO =====");
        DriverManager.quitDriver();
    }
}
