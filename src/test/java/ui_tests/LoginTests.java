package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {
    @Test
    public void loginPosTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm("lizkafox@mail.ru", "wertY!23");
    }

    @Test
    public void loginNegTest_wrongPassword() {
        User user = new User("lizkafox@mail.ru", "QwertY!23");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginFormWithUser(user);
    }
}
