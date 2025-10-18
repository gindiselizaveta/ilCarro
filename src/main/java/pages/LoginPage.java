package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "#email")
    WebElement inputEmail;

    @FindBy(css = "#password")
    WebElement inputPassword;

    @FindBy(css = "button[type='submit']")
    WebElement btnLoginForm;

    public void typeLoginForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnLoginForm.click();
    }

    public void typeLoginFormWithUser(User user) {
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        btnLoginForm.click();
    }
}
