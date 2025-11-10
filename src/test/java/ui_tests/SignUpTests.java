package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignUpPage;
import utils.TestNGListener;

import static utils.UserFactiry.*;

@Listeners(TestNGListener.class)
public class SignUpTests extends ApplicationManager {

    SignUpPage signUpPage;

    @BeforeMethod
    public void goToSignUpPage() {
        new HomePage(getDriver()).clickBtnSignUpHeader();
        signUpPage = new SignUpPage(getDriver());
    }

    @Test
    public void regPositiveTest() {
        User user = positiveUser();
        signUpPage.typeRegForm(user);
        signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextDialogContainerPresents());
    }

    @Test
    public void regNegativeTest_emptyName() {
        User user = positiveUser();
        user.setFirstName("");
        signUpPage.typeRegForm(user);
        signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Name is required"));
    }

    @Test
    public void regNegativeTest_emptyLastName() {
        User user = positiveUser();
        user.setLastName("");
        signUpPage.typeRegForm(user);
        signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Last name is required"));
    }

    @Test
    public void regNegativeTest_emptyEmail() {
        User user = positiveUser();
        user.setUsername("");
        signUpPage.typeRegForm(user);
        signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Email is required"));
    }

    @Test
    public void regNegativeTest_EmailWOAt() {
        User user = positiveUser();
        user.setUsername("lizkafoxmail.ru");
        signUpPage.typeRegForm(user);
        signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Wrong email format"));
    }

    @Test
    public void regNegativeTest_EmptyPassword() {
        User user = positiveUser();
        user.setPassword("");
        signUpPage.typeRegForm(user);
        signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Password is required"));
    }

    @Test
    public void regNegativeTest_WOCheckBoxAction() {
        User user = positiveUser();
        signUpPage.typeRegForm(user);
        //signUpPage.clickCheckBoxAction();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isBtnYallaDisabled(), "Please click on the agreement check box!");
    }

}
