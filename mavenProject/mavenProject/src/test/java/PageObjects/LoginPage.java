package PageObjects;

import org.openqa.selenium.WebDriver;


public class LoginPage {
    public DSL dsl;


    public LoginPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    private void setUsername(String username) {
        dsl.write("user-name", username);
    }

    private void setPassword(String password) {
        dsl.write("password", password);
    }

    private void clickLoginButton() {
        dsl.clickButton("login-button");
    }

    public void doLogin(String userName, String password) {
        setUsername(userName);
        setPassword(password);
        clickLoginButton();
    }
}
