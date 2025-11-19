package pageObjectModels;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SauceDemoHomePage {

    private static WebDriver driver;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(className = "login_wrapper")
    private WebElement loginWrapper;

    @FindBy(className = "login_wrapper-inner")
    private WebElement loginWrapperInner;

    @FindBy(className = "login_button_container")
    private WebElement loginButtonContainer;

    @FindBy(className = "login-box")
    private WebElement loginBox;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement errorMessage;
}
