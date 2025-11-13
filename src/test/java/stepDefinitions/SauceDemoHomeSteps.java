package stepDefinitions;

import driver.DriverManager;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjectModels.SauceDemoHomePage;

@Slf4j
public class SauceDemoHomeSteps {

    private WebDriver driver;
    private SauceDemoHomePage homePage;

    @Given("mi trovo sulla pagina di login SauceDemo")
    public void mi_trovo_sulla_pagina_di_login_sauce_demo() {
        driver = DriverManager.getDriver();
        driver.get("https://www.saucedemo.com/");

        homePage = PageFactory.initElements(driver, SauceDemoHomePage.class);

        log.info("Apro la login page di SauceDemo");
        Assert.assertTrue(homePage.getLoginLogo().isDisplayed());
    }

    @When("inserisco username {string}")
    public void inserisco_username(String username) {
        log.info("Inserisco username: {}", username);
        homePage.getUserName().clear();
        homePage.getUserName().sendKeys(username);
    }

    @When("inserisco password {string}")
    public void inserisco_password(String password) {
        log.info("Inserisco password");
        homePage.getPassword().clear();
        homePage.getPassword().sendKeys(password);
    }

    @When("clicco sul pulsante login")
    public void clicco_sul_pulsante_login() {
        log.info("Clicco sul bottone login");
        homePage.getLoginButton().click();
    }

    @Then("vengo reindirizzato alla pagina Products")
    public void vengo_reindirizzato_alla_pagina_products() {
        log.info("Verifico che la pagina Products sia visibile");

        Assert.assertTrue(
                "Non sei sulla pagina Products!",
                driver.getCurrentUrl().contains("inventory.html")
        );
    }

    @Then("compare il messaggio di errore {string}")
    public void compare_il_messaggio_di_errore(String expectedError) {
        log.info("Verifico il messaggio di errore");

        String actualError = homePage.getErrorMessage().getText();
        Assert.assertEquals(expectedError, actualError);
    }
}
