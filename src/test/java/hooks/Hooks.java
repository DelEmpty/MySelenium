package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

    @Before
    public void setUp() {
        log.info("===== INIZIO SCENARIO =====");
        DriverManager.initDriver();
    }

    @After
    public void tearDown() {
        log.info("===== FINE SCENARIO =====");
        DriverManager.quitDriver();
    }
}
