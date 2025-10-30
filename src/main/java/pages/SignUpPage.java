package pages;

import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import javax.swing.*;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "a[href='/registration?url=%2Fsearch']")
    WebElement btnRegHeader;

    public void clickBtnSignUpHeader() {
        btnRegHeader.click();
    }

    @FindBy(id = "name")
    WebElement inputName;
    @FindBy(id = "lastName")
    WebElement inputlastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(css = "label.terms-label")
    WebElement checkBox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//h1[text()='Registered']")
    WebElement popUpTextRegSuccess;
    @FindBy(xpath = "//mat-dialog-container//h2")
    WebElement textDialogContainer;


    public void typeRegForm(User user) {
        inputName.sendKeys(user.getFirstName());
        inputlastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    //    public void clickCheckBox (){
//        checkBox.click();
//    }
    public void clickCheckBoxAction() {

        int y = checkBox.getSize().getHeight();
        int x = checkBox.getSize().getWidth();
        System.out.println(x + "X" + y);

        Actions actions = new Actions(driver);
        actions.moveToElement(checkBox, (x / 10 * 4), (y / 4)).click().perform();
    }

    public void clickBtnYalla() {
        btnYalla.click();
    }


    public boolean isTextDialogContainerPresents() {
        return elementIsDisplayed(textDialogContainer);
    }

    public boolean isBtnYallaDisabled() {
        String disabledAttr = btnYalla.getAttribute("disabled");
        return disabledAttr != null;
    }


}
