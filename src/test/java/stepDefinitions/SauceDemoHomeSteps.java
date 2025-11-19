package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjectModels.SauceDemoHomePage;

import java.time.Duration;

@Slf4j
public class SauceDemoHomeSteps {

    private WebDriver driver;
    private SauceDemoHomePage homePage;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Given("mi trovo sulla pagina di login SauceDemo")
    public void miTrovoSullaPaginaDiLoginSauceDemo() {
        driver = DriverManager.getDriver();
        driver.get("https://www.saucedemo.com/");

        homePage = PageFactory.initElements(driver, SauceDemoHomePage.class);

        log.info("Apro la login page di SauceDemo");
        wait.until(ExpectedConditions.visibilityOf(homePage.getLoginLogo()));
        
        Assert.assertTrue(homePage.getLoginLogo().isDisplayed());
    }

    @When("inserisco username {string}")
    public void inseriscoUsername(String username) {

        wait.until(ExpectedConditions.visibilityOf(homePage.getUserName()));

        log.info("Inserisco username: {}", username);
        homePage.getUserName().clear();
        homePage.getUserName().sendKeys(username);
    }

    @When("inserisco password {string}")
    public void inseriscoPassword(String password) {

        wait.until(ExpectedConditions.visibilityOf(homePage.getPassword()));

        log.info("Inserisco password");
        homePage.getPassword().clear();
        homePage.getPassword().sendKeys(password);
    }

    @When("clicco sul pulsante login")
    public void cliccoSulPulsanteDiLogin() {

        wait.until(ExpectedConditions.visibilityOf(homePage.getLoginButton()));

        log.info("Clicco sul bottone login");
        homePage.getLoginButton().click();
    }

    @Then("vengo reindirizzato alla pagina Products")
    public void vengoReindirizzatoAllaPaginaProducts() throws InterruptedException {
        Thread.sleep(5000);
        log.info("Verifico che la pagina Products sia visibile");

        Assert.assertTrue(
                "Non sei sulla pagina Products!",
                driver.getCurrentUrl().contains("inventory.html")
        );
    }

    @Then("compare il messaggio di errore {string}")
    public void compareIlMessaggioDiErrore(String expectedError) {

        wait.until(ExpectedConditions.visibilityOf(homePage.getErrorMessage()));

        log.info("Verifico il messaggio di errore");

        String actualError = homePage.getErrorMessage().getText();
        Assert.assertEquals(expectedError, actualError);
    }
}
