package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "a[href='/registration?url=%2Fsearch']")
    WebElement btnRegHeader;

    public void clickBtnSignUpHeader() {
        btnRegHeader.click();
    }

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(css = "#lastName")
    WebElement inputlastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(css = "#password")
    WebElement inputPassword;

    @FindBy(css = "label.terms-label")
    WebElement checkBox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//h1[text()='Registered']")
    WebElement popUpTextRegSuccess;

    public void typeRegForm(User user) {
        inputName.sendKeys(user.getFirstName());
        inputlastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        checkBox.click();
        btnYalla.click();
    }
    public boolean isRegDisplayed() {
        return elementIsDisplayed(popUpTextRegSuccess);
    }

}
