package ui_tests;

import lombok.*;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;

public class LoginTests extends ApplicationManager {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPosTest() {
        User user = User.builder().username("lizkafox@mail.ru").password("wertY!23").build();

        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedDisplayed());
    }

    @Test
    public void loginNegTestWrongPassword() {
        User user = User.builder().username("lizkafox@mail.ru").password("WrongPassword").build();

        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedIncorrect());
    }

    @Test
    public void loginNegTest_EmptyPassword() {
        User user = User.builder().username("lizkafox@mail.ru").password("").build();

        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
    }

    @Test
    public void loginNegTest_emailWOAt() {
        User user = User.builder().username("lizkafoxmail.ru").password("wertY!23").build();

        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"));
    }

    @Test
    public void loginNegTest_EmptyEmail() {
        User user = User.builder().username("").password("wertY!23").build();

        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("Email is required"));
    }

    @Test
    public void loginNegTest_WrongEmail() {
        User user = User.builder().username("lizkawolf@mail.ru").password("wertY!23").build();

        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedIncorrect());
    }

}
