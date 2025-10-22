package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

import static pages.BasePage.setDriver;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void regPositiveTest() {
        User user = new User("lizkafox3@mail.ru", "Qwerty!09", "Lizka", "Fox");
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.clickBtnSignUpHeader();
        registrationPage.typeRegForm(user);
        Assert.assertTrue(registrationPage.isRegDisplayed());
    }

}
