package ui_tests;

import data_providers.CarDP;
import dto.NewCar;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import static pages.BasePage.clickButtonHeader;

@Listeners(TestNGListener.class)
public class AddCarTests extends ApplicationManager {

    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void login() throws IllegalAccessException {
        User user = User.builder().username("lizkafox@mail.ru").password("wertY!23").build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        letTheCarWorkPage = clickButtonHeader(HeaderMenuItem.LETTHECARWORK);
    }

    @Test
    public void addNewCarPositiveTest() {
        NewCar newCar = NewCar.builder().serialNumber("5229630").manufacture("Toyota").model("Yaris Cross").year("2022").fuel("Hybrid").seats(4).carClass("J").pricePerDay(350.0).about("Some String About car").city("Haifa").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isBtnSubmitDisabled());
    }

    @Test(dataProvider = "dataProviderCarFile", dataProviderClass = CarDP.class)
    public void addNewCarPosDataProvider(NewCar car) {
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage.isBtnSubmitDisabled());
    }
}
